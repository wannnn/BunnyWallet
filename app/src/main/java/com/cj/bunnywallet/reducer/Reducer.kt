package com.cj.bunnywallet.reducer

import kotlinx.coroutines.flow.StateFlow

interface Reducer<S : UiState> {
    val uiState: StateFlow<S> // UI StateFlow

    val curState: S // current UI state

    fun setState(newState: S)
}