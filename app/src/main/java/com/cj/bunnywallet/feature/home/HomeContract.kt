package com.cj.bunnywallet.feature.home

import com.cj.bunnywallet.reducer.UiState

sealed class HomeEvent {
    object NavToManageCrypto: HomeEvent()
}

data class HomeState(
    val balance: String = ""
): UiState