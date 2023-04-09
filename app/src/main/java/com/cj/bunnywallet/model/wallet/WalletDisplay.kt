package com.cj.bunnywallet.model.wallet

data class WalletDisplay(
    val id: String = "",
    val name: String = "",
    val amount: String = "--",
    val accounts: List<AccountDisplay> = emptyList(),
    val isExpand: Boolean = false,
) {
    data class AccountDisplay(
        val address: String = "",
        val name: String = "",
        val amount: String = "--",
    )
}
