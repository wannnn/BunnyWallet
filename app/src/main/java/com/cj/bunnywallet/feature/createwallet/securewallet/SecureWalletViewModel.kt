package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.R
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecureWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel(),
    AppNavigator by appNavigator,
    Reducer<SecureWalletState> by ReducerImp(SecureWalletState()) {

    private val pwd: String = savedStateHandle[CreateWalletRoute.SecureWallet.PWD] ?: ""

    fun handleEvent(e: SecureWalletEvent) {
        uiState = when (e) {
            is SecureWalletEvent.UpdateStep -> uiState.copy(
                step = e.step,
                confirmBtnEnable = false,
                confirmErrMsgRes = null,
            )

            is SecureWalletEvent.UpdateConfirmPwd -> {
                val isValid = e.confirmPwd == pwd
                uiState.copy(
                    confirmBtnEnable = isValid,
                    confirmErrMsgRes = R.string.pwd_not_match.takeIf { !isValid }
                )
            }

            is SecureWalletEvent.HandleDialog -> uiState.copy(dialogType = e.dialogType)
        }
    }
}