package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.R
import com.cj.bunnywallet.extensions.isPasswordValid
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePwdViewModel @Inject constructor(appNavigator: AppNavigator) :
    ViewModel(),
    AppNavigator by appNavigator,
    Reducer<CreatePwdState> by ReducerImp(CreatePwdState()) {

    fun handleEvent(e: CreatePwdEvent) {
        when (e) {
            is CreatePwdEvent.SetPwd -> {
                val isValidPwd = e.pwd.isPasswordValid()
                val pwdErrMsgRes = if (isValidPwd) null else R.string.password_condition_hint
                uiState = uiState.copy(
                    pwd = e.pwd,
                    pwdErrMsgRes = pwdErrMsgRes,
                    pwdIsValid = isValidPwd,
                )
            }

            is CreatePwdEvent.SetConfirmPwd -> {
                val isValidConfirmPwd = e.confirmPwd == uiState.pwd
                val pwdErrMsgRes = if (isValidConfirmPwd) null else R.string.pwd_not_match
                uiState = uiState.copy(
                    confirmPwd = e.confirmPwd,
                    confirmPwdErrMsgRes = pwdErrMsgRes,
                    confirmPwdIsValid = isValidConfirmPwd,
                )
            }

            is CreatePwdEvent.SetPwdVisibility ->
                uiState = uiState.copy(pwdVisibility = e.isVisible)

            is CreatePwdEvent.SetBiometrics ->
                uiState = uiState.copy(bioEnabled = e.isEnable)

            is CreatePwdEvent.SetCheckDeclaration ->
                uiState = uiState.copy(declarationChecked = e.isCheck)

            CreatePwdEvent.CreatePwd ->
                navigateTo(
                    NavEvent.NavTo(
                        CreateWalletRoute.SecureWallet.genRoute(uiState.pwd),
                    ),
                )
        }
    }

}