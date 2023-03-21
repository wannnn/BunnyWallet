package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import org.web3j.crypto.MnemonicUtils
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class SecureWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<SecureWalletState> by ReducerImp(SecureWalletState()) {

    private val pwd: String = savedStateHandle[CreateWalletRoute.SecureWallet.PWD] ?: ""

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

            SecureWalletEvent.RevealSRP ->
                uiState = uiState.copy(mnemonic = generateMnemonic())

            SecureWalletEvent.NavToConfirmSRP -> {
                val srp = uiState.mnemonic.toString()
                navigateTo(NavEvent.NavTo(route = CreateWalletRoute.ConfirmSRP.genRoute(srp)))
            }

        }
    }

    private fun generateMnemonic(): List<String> =
        ByteArray(BYTE_SIZE).let {
            SecureRandom().nextBytes(it)
            MnemonicUtils.generateMnemonic(it).split(" ")
        }

    private companion object {
        const val BYTE_SIZE = 16
    }
}