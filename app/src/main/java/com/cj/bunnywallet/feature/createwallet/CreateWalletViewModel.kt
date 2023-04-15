package com.cj.bunnywallet.feature.createwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val web3jManager: Web3jManager,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<CreateWalletState> by ReducerImp(CreateWalletState()) {

    fun handleEvent(e: CreateWalletEvent) {
        when (e) {
            CreateWalletEvent.RevealSRP ->
                uiState = uiState.copy(mnemonic = web3jManager.generateMnemonicList())

            CreateWalletEvent.Continue -> viewModelScope.launch {
                val wallet = web3jManager.mnemonicList2String(uiState.mnemonic)
                    .let { mnemonic -> web3jManager.createWallet(mnemonic) }
                    ?: run {
                        // TODO Show Toast create wallet failed plz try again
                        return@launch
                    }
                walletDS.createWallet(wallet)
                navToHome()
            }
        }
    }

    private fun navToHome() {
        navigateTo(
            NavEvent.NavTo(
                route = MainRoute.Home.route,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(route = MainRoute.WalletSetup.route, inclusive = true)
                    .build(),
            )
        )
    }
}