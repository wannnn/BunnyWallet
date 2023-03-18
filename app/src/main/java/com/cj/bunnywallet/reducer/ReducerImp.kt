package com.cj.bunnywallet.reducer

import com.cj.bunnywallet.helper.StateFlowDelegate
import kotlinx.coroutines.flow.StateFlow

class ReducerImp<S : UiState>(initState: S) : Reducer<S> {

    private val delegate = StateFlowDelegate(initState)
    private var state by delegate

    override val uiStateFlow: StateFlow<S> = delegate.stateFlow

    override var uiState: S
        get() = state
        set(value) {
            state = value
        }
}