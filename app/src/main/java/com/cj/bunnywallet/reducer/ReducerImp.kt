package com.cj.bunnywallet.reducer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

class ReducerImp<S: UiState>(initState: S): Reducer<S> {

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initState)

    override val uiState: StateFlow<S> = _uiState.asStateFlow()

    override fun setState(newState: S) {
        _uiState.updateAndGet { newState }
    }

    override fun getState(): S = _uiState.value

}