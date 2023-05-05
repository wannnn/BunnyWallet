package com.cj.bunnywallet.feature.customcrypto

import com.cj.bunnywallet.reducer.UiState

sealed class CustomCryptoEvent {
    data class InputAddress(val addr: String): CustomCryptoEvent()
    data class InputSymbol(val symbol: String): CustomCryptoEvent()
    data class InputDecimal(val decimal: String): CustomCryptoEvent()
    object Confirm: CustomCryptoEvent()
}

data class CustomCryptoState(
    val isLoading: Boolean = false,
    val address: String = "",
    val symbol: String = "",
    val decimal: String = "",
    val btnEnable: Boolean = false,
) : UiState