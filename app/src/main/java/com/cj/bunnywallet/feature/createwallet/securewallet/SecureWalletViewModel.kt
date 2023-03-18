package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecureWalletViewModel @Inject constructor(appNavigator: AppNavigator) : ViewModel(),
    AppNavigator by appNavigator,
    Reducer<SecureWalletState> by ReducerImp(SecureWalletState()) {

    fun handleEvent(e: SecureWalletEvent) {
        when (e) {
            is SecureWalletEvent.SetPwd -> setState(curState.copy(pwd = e.pwd))

            SecureWalletEvent.OpenSRPDialog ->
                setState(curState.copy(dialogType = SecureWalletDialogType.SRP))

            SecureWalletEvent.OpenProtectWalletInfoDialog ->
                setState(curState.copy(dialogType = SecureWalletDialogType.PROTECT_WALLET_INFO))

            SecureWalletEvent.OpenWarnSkipDialog ->
                setState(curState.copy(dialogType = SecureWalletDialogType.WARN_SKIP))

            SecureWalletEvent.HideDialog ->
                setState(curState.copy(dialogType = SecureWalletDialogType.HIDE))
        }
    }
}