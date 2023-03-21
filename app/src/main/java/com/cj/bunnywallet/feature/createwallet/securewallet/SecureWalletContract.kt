package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.annotation.StringRes
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.reducer.UiState

sealed interface SecureWalletEvent {
    data class UpdateStep(val step: SecureWalletStep) : SecureWalletEvent
    data class UpdateConfirmPwd(val confirmPwd: String) : SecureWalletEvent
    data class HandleDialog(val dialogType: SecureWalletDialogType) : SecureWalletEvent

    object RevealSRP: SecureWalletEvent
    object SkipGenSRP: SecureWalletEvent
    object NavToConfirmSRP: SecureWalletEvent
}

enum class SecureWalletStep {
    READ_SECURE_INFO,
    CONFIRM_PWD,
    GEN_SRP,
}

data class SecureWalletState(
    val step: SecureWalletStep = SecureWalletStep.READ_SECURE_INFO,
    val confirmBtnEnable: Boolean = false,
    @StringRes val confirmErrMsgRes: Int? = null,
    val mnemonic: List<String> = emptyList(),
    val dialogType: SecureWalletDialogType = SecureWalletDialogType.HIDE,
) : UiState