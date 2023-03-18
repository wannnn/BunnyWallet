package com.cj.bunnywallet.reducer

import kotlinx.coroutines.flow.StateFlow

interface Reducer<S : UiState> {
    val uiStateFlow: StateFlow<S> // UI StateFlow

    var uiState: S // current UI state
}