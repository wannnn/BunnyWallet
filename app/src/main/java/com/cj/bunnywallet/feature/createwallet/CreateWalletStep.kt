package com.cj.bunnywallet.feature.createwallet

enum class CreateWalletStep(val stepNum: String) {
    CREATE_PWD("1"),
    SECURE_WALLET("2"),
    CONFIRM_SRP("3"),
    DONE("4"),
}