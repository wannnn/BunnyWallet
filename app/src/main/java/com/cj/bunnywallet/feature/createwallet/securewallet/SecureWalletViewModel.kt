package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.R
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.web3j.crypto.MnemonicUtils
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class SecureWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    dataStore: BunnyDataStore,
    manager: CryptoManager,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<SecureWalletState> by ReducerImp(SecureWalletState()) {

    private var pwd = ""

    init {
        dataStore.getString(KEY_PWD)
            .onEach { pwd = manager.decrypt(it).orEmpty() }
            .launchIn(viewModelScope)
    }

    fun handleEvent(e: SecureWalletEvent) {
        when (e) {
            is SecureWalletEvent.UpdateStep -> uiState = uiState.copy(
                step = e.step,
                confirmBtnEnable = false,
                confirmErrMsgRes = null,
            )

            is SecureWalletEvent.UpdateConfirmPwd -> {
                val isValid = e.confirmPwd == pwd
                uiState = uiState.copy(
                    confirmBtnEnable = isValid,
                    confirmErrMsgRes = R.string.pwd_not_match.takeIf { !isValid },
                )
            }

            is SecureWalletEvent.HandleDialog -> uiState = uiState.copy(dialogType = e.dialogType)

            SecureWalletEvent.SkipGenSRP -> {
                uiState = uiState.copy(dialogType = SecureWalletDialogType.HIDE)
                val navEvent = NavEvent.NavTo(
                    route = MainRoute.Entrance.route,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(route = MainRoute.Entrance.route, inclusive = true).build(),
                )
                navigateTo(navEvent)
            }

            SecureWalletEvent.RevealSRP -> uiState = uiState.copy(mnemonic = generateMnemonic())

            SecureWalletEvent.NavToConfirmSRP -> {
                val srp = uiState.mnemonic.joinToString(separator = " ")
                navigateTo(NavEvent.NavTo(route = CreateWalletRoute.ConfirmSRP.genRoute(srp)))
            }
        }
    }

    private fun generateMnemonic(): List<String> = ByteArray(BYTE_SIZE).let {
        SecureRandom().nextBytes(it)
        MnemonicUtils.generateMnemonic(it).split(" ")
    }

    private companion object {
        const val BYTE_SIZE = 16
    }
}