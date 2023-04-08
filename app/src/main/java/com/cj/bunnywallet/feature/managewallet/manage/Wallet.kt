package com.cj.bunnywallet.feature.managewallet.manage

data class Wallet(
    val id: String = "",
    val name: String = "",
    val accounts: List<Account> = emptyList(),
    val isExpand: Boolean = false,
) {
    val amount: String get() = accounts.sumOf { it.amount }.toString()

    data class Account(
        val address: String,
        val name: String = "",
        val amount: Double = 0.0,
    )
}
