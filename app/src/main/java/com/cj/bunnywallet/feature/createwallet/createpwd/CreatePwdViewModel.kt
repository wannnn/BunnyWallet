package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.R
import com.cj.bunnywallet.extensions.isPasswordValid
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Eagerly
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreatePwdViewModel @Inject constructor(appNavigator: AppNavigator) :
    ViewModel(),
    AppNavigator by appNavigator,
    Reducer<CreatePwdState> by ReducerImp(CreatePwdState()) {

    private val pwdValidState = MutableStateFlow(false)
    private val confirmPwdValidState = MutableStateFlow(false)
    private val declarationCheckedState = MutableStateFlow(false)
    private val createPwdBtnEnableState = combine(
        pwdValidState,
        confirmPwdValidState,
        declarationCheckedState,
    ) { isValidPwd, isValidConfirmPwd, declarationChecked ->
        isValidPwd && isValidConfirmPwd && declarationChecked
    }
        .stateIn(viewModelScope, Eagerly, false)

    fun handleEvent(e: CreatePwdEvent) {
        when (e) {
            is CreatePwdEvent.SetPwd -> {
                val isValidPwd = e.pwd.isPasswordValid()
                val pwdErrMsgRes = if (isValidPwd) null else R.string.password_condition_hint
                pwdValidState.tryEmit(isValidPwd)
                setState(
                    curState.copy(
                        pwd = e.pwd,
                        pwdErrMsgRes = pwdErrMsgRes,
                        createPwdBtnEnabled = createPwdBtnEnableState.value,
                    ),
                )
            }

            is CreatePwdEvent.SetConfirmPwd -> {
                val isValidConfirmPwd = e.confirmPwd == curState.pwd
                val pwdErrMsgRes = if (isValidConfirmPwd) null else R.string.pwd_not_match
                confirmPwdValidState.tryEmit(isValidConfirmPwd)
                setState(
                    curState.copy(
                        confirmPwd = e.confirmPwd,
                        confirmPwdErrMsgRes = pwdErrMsgRes,
                        createPwdBtnEnabled = createPwdBtnEnableState.value,
                    ),
                )
            }

            is CreatePwdEvent.SetPwdVisibility ->
                setState(curState.copy(pwdVisibility = e.isVisible))

            is CreatePwdEvent.SetBiometrics ->
                setState(curState.copy(bioEnabled = e.isEnable))

            is CreatePwdEvent.SetCheckDeclaration -> {
                declarationCheckedState.tryEmit(e.isCheck)
                setState(
                    curState.copy(
                        declarationChecked = e.isCheck,
                        createPwdBtnEnabled = createPwdBtnEnableState.value,
                    ),
                )
            }

            CreatePwdEvent.CreatePwd ->
                navigateTo(
                    NavEvent.NavTo(
                        CreateWalletRoute.SecureWallet.genRoute(curState.pwd),
                    ),
                )
        }
    }

}