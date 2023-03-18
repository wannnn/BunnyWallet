package com.cj.bunnywallet.feature.createwallet.securewallet

import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.reducer.UiState

sealed class SecureWalletEvent {
    data class SetPwd(val pwd: String) : SecureWalletEvent()
    object OpenSRPDialog : SecureWalletEvent()
    object OpenProtectWalletInfoDialog : SecureWalletEvent()
    object OpenWarnSkipDialog : SecureWalletEvent()
    object HideDialog : SecureWalletEvent()
}

data class SecureWalletState(
    val pwd: String = "",
    val dialogType: SecureWalletDialogType = SecureWalletDialogType.HIDE,
) : UiState