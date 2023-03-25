package com.cj.bunnywallet.feature.importwallet

import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.reducer.UiState

sealed class ImportWalletEvent {
    data class SetPhraseAmountType(val phraseAmount: PhraseAmountType): ImportWalletEvent()
    data class SetPassword(val pwd: String): ImportWalletEvent()
    data class SetConfirmPassword(val pwd: String): ImportWalletEvent()
    data class UpdatePhrase(val index: Int, val phrase: String): ImportWalletEvent()
    object Import: ImportWalletEvent()
}

data class ImportWalletState(
    val phraseAmount: PhraseAmountType = PhraseAmountType.TWELVE_WORDS,
    val showSetPwd: Boolean = true,
    val pwd: String = "",
    val pwdValid: Boolean = true,
    val confirmPwd: String = "",
): UiState {
    private val confirmPwdValid: Boolean
        get() = pwd == confirmPwd
    val pwdErrMsg: Int?
        get() = if (pwdValid) null else R.string.password_condition_hint
    val confirmPwdErrMsg: Int?
        get() = if (confirmPwdValid) null else R.string.pwd_not_match
    val btnEnable: Boolean
        get() = pwd.isNotBlank() && pwdValid && confirmPwdValid
}