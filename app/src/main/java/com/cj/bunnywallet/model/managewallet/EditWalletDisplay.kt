package com.cj.bunnywallet.model.managewallet

data class EditWalletDisplay(
    val id: String, // encrypted mnemonic
    val name: String,
    val accounts: List<EditAccountDisplay>,
) {
    data class EditAccountDisplay(
        val address: String,
        val name: String,
    )
}
