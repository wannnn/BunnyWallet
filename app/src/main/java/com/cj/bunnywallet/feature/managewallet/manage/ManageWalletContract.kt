package com.cj.bunnywallet.feature.managewallet.manage

import com.cj.bunnywallet.reducer.UiState

sealed interface ManageWalletEvent {
    data class ExpandWallet(val walletId: String) : ManageWalletEvent
    data class SelectAccount(val address: String) : ManageWalletEvent
    data class AddAccount(val walletId: String) : ManageWalletEvent

    object OnBackClick : ManageWalletEvent
}

data class ManageWalletState(
    val wallets: List<Wallet> = emptyList(),
    val currentAccount: String = "",
) : UiState