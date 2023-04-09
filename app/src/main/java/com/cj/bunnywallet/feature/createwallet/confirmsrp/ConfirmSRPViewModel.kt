package com.cj.bunnywallet.feature.createwallet.confirmsrp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_MNEMONIC
import com.cj.bunnywallet.datasource.BunnyPreferencesDataStore
import com.cj.bunnywallet.extensions.indexOfFirstOrNull
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmSRPViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
    private val dataStore: BunnyPreferencesDataStore,
    private val manager: CryptoManager,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<ConfirmSRPState> by ReducerImp(ConfirmSRPState()) {

    private val mnemonic = savedStateHandle.get<String>(CreateWalletRoute.ConfirmSRP.SRP)
        ?.split(" ")
        .orEmpty()

    init {
        genShuffledMnemonic()
    }

    private fun genShuffledMnemonic() {
        mnemonic.shuffled()
            .mapIndexed { index, phrase -> PhraseSlot(index, phrase) }
            .let { uiState = uiState.copy(shuffledMnemonic = it) }
    }

    fun handleEvent(e: ConfirmSRPEvent) {
        when (e) {
            is ConfirmSRPEvent.OnSlotClicked -> {
                if (e.ps.selected) return
                handleSlotClicked(ps = e.ps)
            }

            is ConfirmSRPEvent.OnShuffledPhraseClicked -> handleShuffleClicked(ps = e.ps)

            ConfirmSRPEvent.BackUpCompleted -> {
                val encryptedMnemonic =
                    manager.encrypt(mnemonic.joinToString(separator = " ")) ?: return
                viewModelScope.launch {
                    dataStore.putString(KEY_MNEMONIC, encryptedMnemonic)
                    val destination = NavEvent.NavTo(
                        route = CreateWalletRoute.Completed.route,
                        navOptions = NavOptions.Builder().setPopUpTo(
                            route = MainRoute.WalletSetup.route,
                            inclusive = true,
                        ).build()
                    )
                    navigateTo(destination)
                }
            }
        }
    }

    private fun handleSlotClicked(ps: PhraseSlot) {
        val newSelectedList = uiState.selectedMnemonic.toMutableList()
        val newShuffledList = uiState.shuffledMnemonic.toMutableList()

        // Set the current focused slot as unfocused
        uiState.selectedMnemonic.indexOfFirstOrNull { it.selected }
            ?.let { newSelectedList[it] = newSelectedList[it].copy(selected = false) }

        // Clear the picked slot's phrase also set the slot as focused
        newSelectedList[ps.pos] = newSelectedList[ps.pos].copy(phrase = "", selected = true)

        // If the picked slot has phrase,
        // then need to set the phrase as unselected in shuffled list
        if (ps.phrase.isNotBlank()) {
            uiState.shuffledMnemonic.indexOfFirstOrNull { it.phrase == ps.phrase }
                ?.let { newShuffledList[it] = newShuffledList[it].copy(selected = false) }
        }

        uiState = uiState.copy(
            selectedMnemonic = newSelectedList,
            shuffledMnemonic = newShuffledList,
            sprSelectedState = SRPSelectedState.UNDONE,
        )
    }

    private fun handleShuffleClicked(ps: PhraseSlot) {
        val newShuffledList = uiState.shuffledMnemonic.toMutableList()
        val newSelectedList = uiState.selectedMnemonic.toMutableList()

        var newSprSelectedState = SRPSelectedState.UNDONE

        // Set picked phrase's status to the opposite
        newShuffledList[ps.pos] = newShuffledList[ps.pos].copy(selected = !ps.selected)

        if (ps.selected) {
            // If the picked phrase's selected is true, that means it's gonna be unselected

            // Set the current focused slot as unfocused
            uiState.selectedMnemonic.indexOfFirstOrNull { it.selected }
                ?.let { newSelectedList[it] = newSelectedList[it].copy(selected = false) }

            // Find the slot which is filled in this phrase,
            // then clear the phrase also set the slot as focused
            uiState.selectedMnemonic.indexOfFirstOrNull { it.phrase == ps.phrase }
                ?.let {
                    newSelectedList[it] = newSelectedList[it].copy(phrase = "", selected = true)
                }
        } else {
            // If the picked phrase's selected is false, that means it's gonna be selected

            // Fill the picked phrase into the current focused slot
            uiState.selectedMnemonic.indexOfFirstOrNull { it.selected }
                ?.let {
                    newSelectedList[it] =
                        newSelectedList[it].copy(phrase = ps.phrase, selected = false)
                }

            // Set the first blank slot as focused
            newSelectedList.indexOfFirstOrNull { it.phrase.isBlank() }
                ?.let { newSelectedList[it] = newSelectedList[it].copy(selected = true) }
                ?: run {
                    // If all slot are filled, check mnemonic order
                    newSprSelectedState =
                        when (newSelectedList.map { it.phrase } == mnemonic) {
                            true -> SRPSelectedState.CORRECT
                            else -> SRPSelectedState.WRONG
                        }
                }
        }

        uiState = uiState.copy(
            selectedMnemonic = newSelectedList,
            shuffledMnemonic = newShuffledList,
            sprSelectedState = newSprSelectedState,
        )
    }
}