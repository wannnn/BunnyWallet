package com.cj.bunnywallet.feature.home

import com.cj.bunnywallet.reducer.UiState

sealed class HomeEvent {
    // TODO I put it just for pass detekt
}

data class HomeState(
    val balance: String = ""
): UiState