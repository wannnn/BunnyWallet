package com.cj.bunnywallet.feature.importwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.KEY_MNEMONIC
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    init {
        dataStore.getString(KEY_PWD)
            .onEach { uiState = uiState.copy(showSetPwd = it.isBlank()) }
            .launchIn(viewModelScope)
    }

    fun handleEvent(event: ImportWalletEvent) {
        when (event) {
            is ImportWalletEvent.SetPhraseAmountType -> {
                uiState = uiState.copy(phraseAmount = event.phraseAmount)
                phraseList = MutableList(size = event.phraseAmount.amount) { "" }
            }
            is ImportWalletEvent.SetPassword -> {
                uiState = uiState.copy(
                    pwd = event.pwd,
                    pwdValid = event.pwd.isPasswordValid()
                )
            }
            is ImportWalletEvent.SetConfirmPassword -> {
                uiState = uiState.copy(confirmPwd = event.pwd)
            }
            is ImportWalletEvent.UpdatePhrase -> {
                phraseList[event.index] = event.phrase
            }
            is ImportWalletEvent.Import -> {
                if (uiState.btnEnable) {
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

        viewModelScope.launch {
            if (uiState.showSetPwd) {
                savePwd()
            }
            saveMnemonic(mnemonic)
            navToHome()
        }
    }

    private suspend fun savePwd() {
        manager.encrypt(uiState.pwd)?.let {
            dataStore.putString(KEY_PWD, it)
        }
    }

    private suspend fun saveMnemonic(mnemonic: String) {
        manager.encrypt(mnemonic)?.let {
            dataStore.putString(KEY_MNEMONIC, it)
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