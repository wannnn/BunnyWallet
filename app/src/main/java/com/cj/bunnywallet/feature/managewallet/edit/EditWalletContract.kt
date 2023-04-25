package com.cj.bunnywallet.feature.managewallet.edit

import com.cj.bunnywallet.model.managewallet.EditInfo
import com.cj.bunnywallet.model.managewallet.EditWalletDisplay
import com.cj.bunnywallet.reducer.UiState

sealed interface EditWalletEvent {
    data class EditWalletName(
        val walletId: String,
        val walletName: String,
    ) : EditWalletEvent

    data class EditAccountName(
        val walletId: String,
        val address: String,
        val accountName: String,
    ) : EditWalletEvent

    data class EditDone(val editInfo: EditInfo) : EditWalletEvent

    data class DeleteWallet(val walletId: String) : EditWalletEvent

    object NoUpdate : EditWalletEvent
}

data class EditWalletState(
    val wallets: List<EditWalletDisplay> = emptyList(),
    val editInfo: EditInfo? = null,
) : UiState

