package com.cj.bunnywallet.feature.createwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.datasource.WalletDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.proto.wallet.account
import com.cj.bunnywallet.proto.wallet.wallet
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val web3jManager: Web3jManager,
    private val cryptoManager: CryptoManager,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<CreateWalletState> by ReducerImp(CreateWalletState()) {

    fun handleEvent(e: CreateWalletEvent) {
        when (e) {
            CreateWalletEvent.RevealSRP ->
                uiState = uiState.copy(mnemonic = web3jManager.generateMnemonicList())

            CreateWalletEvent.Continue -> viewModelScope.launch {
                val wallet = createWallet() ?: run {
                    // TODO Show Toast create wallet failed plz try again
                    return@launch
                }
                walletDS.createWallet(wallet)
                navToHome()
            }
        }
    }

    private fun createWallet(): Wallet? = runCatching {
        val mnemonic = web3jManager.mnemonicList2String(uiState.mnemonic)
        val credentials = web3jManager.loadCredentials(mnemonic)
        val encryptedMnemonic = cryptoManager.encrypt(mnemonic)
            ?: throw ArithmeticException("Mnemonic encrypted fail")

        wallet {
            id = encryptedMnemonic
            name = FIRST_WALLET
            accounts.put(
                credentials.address,
                account {
                    address = credentials.address
                    name = FIRST_ACCOUNT
                },
            )
            selectedAccount = credentials.address
        }
    }
        .onFailure { Timber.d(it.message) }
        .getOrNull()

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

    private companion object {
        const val FIRST_WALLET = "Wallet 1"
        const val FIRST_ACCOUNT = "Account 1"
    }
}