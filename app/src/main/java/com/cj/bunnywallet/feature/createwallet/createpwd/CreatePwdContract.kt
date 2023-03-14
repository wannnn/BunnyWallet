package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.annotation.StringRes
import com.cj.bunnywallet.reducer.UiState

sealed class CreatePwdEvent {
    data class SetPwd(val pwd: String) : CreatePwdEvent()
    data class SetConfirmPwd(val confirmPwd: String) : CreatePwdEvent()
    data class SetPwdVisibility(val isVisible: Boolean) : CreatePwdEvent()
    data class SetBiometrics(val isEnable: Boolean) : CreatePwdEvent()
    data class SetCheckDeclaration(val isCheck: Boolean) : CreatePwdEvent()
    object CreatePwd : CreatePwdEvent()
}

data class CreatePwdState(
    val pwd: String = "",
    @StringRes val pwdErrMsgRes: Int? = null,
    val confirmPwd: String = "",
    @StringRes val confirmPwdErrMsgRes: Int? = null,
    val samePwd: Boolean = false,
    val pwdVisibility: Boolean = false,
    val bioEnabled: Boolean = true,
    val declarationChecked: Boolean = false,
    val createPwdBtnEnabled: Boolean = false,
) : UiState