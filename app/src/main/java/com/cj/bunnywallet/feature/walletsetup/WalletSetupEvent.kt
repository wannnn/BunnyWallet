package com.cj.bunnywallet.feature.walletsetup

sealed interface WalletSetupEvent {
    object CreateWallet : WalletSetupEvent
    object ImportWallet : WalletSetupEvent
}