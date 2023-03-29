package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.annotation.StringRes
import com.cj.bunnywallet.reducer.UiState

sealed class CreatePwdEvent {
    data class SetPwd(val pwd: String) : CreatePwdEvent()
    data class SetConfirmPwd(val confirmPwd: String) : CreatePwdEvent()
    data class SetBiometrics(val isEnable: Boolean) : CreatePwdEvent()
    data class SetCheckDeclaration(val isCheck: Boolean) : CreatePwdEvent()
    object CreatePwd : CreatePwdEvent()
}

data class CreatePwdState(
    val pwd: String = "",
    @StringRes val pwdErrMsgRes: Int? = null,
    private val pwdIsValid: Boolean = false,
    val confirmPwd: String = "",
    @StringRes val confirmPwdErrMsgRes: Int? = null,
    private val confirmPwdIsValid: Boolean = false,
    val samePwd: Boolean = false,
    val bioEnabled: Boolean = true,
    val declarationChecked: Boolean = false,
) : UiState {
    val createPwdBtnEnabled: Boolean
        get() = pwdIsValid && confirmPwdIsValid && declarationChecked
}