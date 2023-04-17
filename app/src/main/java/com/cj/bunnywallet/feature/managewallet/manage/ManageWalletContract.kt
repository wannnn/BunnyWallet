package com.cj.bunnywallet.feature.managewallet.manage

import com.cj.bunnywallet.feature.managewallet.manage.type.ManageType
import com.cj.bunnywallet.model.wallet.WalletDisplay
import com.cj.bunnywallet.reducer.UiState

sealed interface ManageWalletEvent {
    data class ExpandWallet(val walletId: String) : ManageWalletEvent
    data class SelectAccount(val walletId: String, val address: String) : ManageWalletEvent
    data class AddAccount(val walletId: String) : ManageWalletEvent
    data class MenuItemClick(val type: ManageType) : ManageWalletEvent

    object OnBackClick : ManageWalletEvent
}

data class ManageWalletState(
    val wallets: List<WalletDisplay> = emptyList(),
) : UiState