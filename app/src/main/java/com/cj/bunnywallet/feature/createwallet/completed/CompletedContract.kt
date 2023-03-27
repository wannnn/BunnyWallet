package com.cj.bunnywallet.feature.createwallet.completed

import com.cj.bunnywallet.reducer.UiState

sealed interface CompletedEvent {
    data class HandleDialog(val show: Boolean) : CompletedEvent
    data class SaveHint(val hint: String) : CompletedEvent

    object Done : CompletedEvent
}

data class CompletedState(
    val showDialog: Boolean = false,
) : UiState