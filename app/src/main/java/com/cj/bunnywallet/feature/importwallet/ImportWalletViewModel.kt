package com.cj.bunnywallet.feature.importwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.datasource.WalletDataStore
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportWalletViewModel @Inject constructor(
    navigator: AppNavigator,
    private val web3jManager: Web3jManager,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by navigator,
    Reducer<ImportWalletState> by ReducerImp(ImportWalletState()) {

    private var phraseList: MutableList<String> =
        MutableList(size = PhraseAmountType.TWELVE_WORDS.amount) { "" }

    fun handleEvent(event: ImportWalletEvent) {
        when (event) {
            is ImportWalletEvent.SetPhraseAmountType -> {
                uiState = uiState.copy(phraseAmount = event.phraseAmount)
                phraseList = MutableList(size = event.phraseAmount.amount) { "" }
            }

            is ImportWalletEvent.UpdatePhrase -> {
                phraseList[event.index] = event.phrase
                uiState = uiState.copy(
                    btnEnable = "" !in phraseList,
                    validMnemonic = true,
                )
            }

            is ImportWalletEvent.Import -> viewModelScope.launch {
                val wallet = checkAndCreateWallet() ?: return@launch
                walletDS.createWallet(wallet)
                navToHome()
            }
        }
    }

    private fun checkAndCreateWallet(): Wallet? {
        uiState = uiState.copy(isLoading = true)

        val mnemonic = when {
            // DEBUG Address 0x162521dbAB8D42985159ECB28ABC9447990e527e
            BuildConfig.DEBUG -> BuildConfig.DEBUG_MNEMONIC
            else -> web3jManager.mnemonicList2String(phraseList)
        }

        if (!web3jManager.validateMnemonic(mnemonic)) {
            uiState = uiState.copy(isLoading = false, validMnemonic = false)
            return null
        }

        return web3jManager.createWallet(mnemonic) ?: run {
            uiState = uiState.copy(isLoading = false)
            // TODO Show Toast create wallet failed plz try again
            return null
        }
    }

    private fun navToHome() {
        navigateTo(
            NavEvent.NavTo(
                route = MainRoute.Home.route,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(route = MainRoute.WalletSetup.route, inclusive = true)
                    .build()
            )
        )
    }
}