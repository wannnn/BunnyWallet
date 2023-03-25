package com.cj.bunnywallet.feature.unlock

import com.cj.bunnywallet.reducer.UiState

sealed class UnlockEvent {
    object Unlock : UnlockEvent()
    data class SetPassword(val pwd: String): UnlockEvent()
}

data class UnlockState(
    val pwd: String = "",
    val invalidPwdMsg: Int? = null
): UiState