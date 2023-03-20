package com.cj.bunnywallet.feature.createwallet.securewallet

import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.reducer.UiState

sealed class SecureWalletEvent {
    data class HandleDialog(val dialogType: SecureWalletDialogType) : SecureWalletEvent()
}

data class SecureWalletState(
    val dialogType: SecureWalletDialogType = SecureWalletDialogType.HIDE,
) : UiState