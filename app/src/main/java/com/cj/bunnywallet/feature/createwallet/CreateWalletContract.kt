package com.cj.bunnywallet.feature.createwallet

import com.cj.bunnywallet.reducer.UiState

sealed interface CreateWalletEvent {
    object RevealSRP : CreateWalletEvent
    object Continue : CreateWalletEvent
}

data class CreateWalletState(
    val mnemonic: List<String> = emptyList(),
) : UiState