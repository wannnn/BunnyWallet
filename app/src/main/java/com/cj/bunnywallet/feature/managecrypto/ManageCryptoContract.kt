package com.cj.bunnywallet.feature.managecrypto

import com.cj.bunnywallet.proto.wallet.Crypto
import com.cj.bunnywallet.reducer.UiState

sealed class ManageCryptoEvent {
    // TODO
}

data class ManageCryptoState(
    val added: List<Crypto> = listOf(),
    val popular: List<Crypto> = listOf()
): UiState