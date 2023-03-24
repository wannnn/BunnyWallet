package com.cj.bunnywallet.feature.createwallet.confirmsrp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmSRPViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<ConfirmSRPContractState> by ReducerImp(ConfirmSRPContractState()) {

    private val mnemonic = savedStateHandle.get<String>(CreateWalletRoute.ConfirmSRP.SRP)
            ?.split(" ")
            .orEmpty()

    init {
        genShuffledMnemonic()
    }

    private fun genShuffledMnemonic() {
        mnemonic.shuffled()
            .mapIndexed { index, phrase -> PhraseSlot(index, phrase) }
            .toMutableList()
            .let { uiState = uiState.copy(shuffledMnemonic = it) }
    }

    fun handleEvent(e: ConfirmSRPContractEvent) {
        when (e) {
            ConfirmSRPContractEvent.ToCreateWalletCompleted ->
                navigateTo(NavEvent.NavTo(CreateWalletRoute.CreateWalletCompleted.route))
        }
    }

}