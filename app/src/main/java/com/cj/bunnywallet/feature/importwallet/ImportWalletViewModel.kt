package com.cj.bunnywallet.feature.importwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.extensions.isPasswordValid
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.web3j.crypto.Bip44WalletUtils
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ImportWalletViewModel @Inject constructor(
    private val dataStore: BunnyDataStore,
    private val manager: CryptoManager,
    private val navigator: AppNavigator
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
            is ImportWalletEvent.SetPassword -> {
                uiState = uiState.copy(
                    password = event.pwd,
                    passwordValid = event.pwd.isPasswordValid()
                )
            }
            is ImportWalletEvent.SetConfirmPassword -> {
                uiState = uiState.copy(
                    confirmPassword = event.pwd,
                    confirmPasswordValid = event.pwd.isNotBlank() && event.pwd == uiState.password
                )

            }
            is ImportWalletEvent.UpdatePhrase -> {
                phraseList[event.index] = event.phrase
            }
            is ImportWalletEvent.Import -> {
                if (uiState.passwordValid && uiState.confirmPasswordValid) {
                    importWallet()
                }
            }
        }
    }

    private fun importWallet() {
        val mnemonic = when {
            BuildConfig.DEBUG -> {
                // 0x162521dbAB8D42985159ECB28ABC9447990e527e
                BuildConfig.DEBUG_MNEMONIC
            }
            "" !in phraseList -> phraseList.joinToString(separator = " ")
            else -> return
        }

        val credentials = Bip44WalletUtils.loadBip44Credentials("", mnemonic)

        Timber.d(message = "address: ${credentials.address}")

        manager.encrypt(uiState.password)?.let {
            viewModelScope.launch {
                dataStore.putString(KEY_PWD, it)
                navigateTo(
                    NavEvent.NavTo(
                        route = MainRoute.Home.route,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(route = MainRoute.Entrance.route, inclusive = false)
                            .build()
                    )
                )
            }
        }
    }
}