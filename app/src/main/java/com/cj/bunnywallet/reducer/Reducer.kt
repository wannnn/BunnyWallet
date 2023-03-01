package com.cj.bunnywallet.reducer

import kotlinx.coroutines.flow.StateFlow

interface Reducer<S: UiState> {
    val uiState: StateFlow<S>
    fun setState(newState: S)
    fun getState(): S
}