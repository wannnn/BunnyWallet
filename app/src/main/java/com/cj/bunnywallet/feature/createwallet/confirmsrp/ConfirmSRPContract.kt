package com.cj.bunnywallet.feature.createwallet.confirmsrp

import com.cj.bunnywallet.MNEMONIC_SIZE_12
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot
import com.cj.bunnywallet.reducer.UiState

sealed interface ConfirmSRPEvent {
    data class OnSlotClicked(val ps: PhraseSlot) : ConfirmSRPEvent
    data class OnShuffledPhraseClicked(val ps: PhraseSlot) : ConfirmSRPEvent

    object BackUpCompleted : ConfirmSRPEvent
}

data class ConfirmSRPState(
    val selectedMnemonic: List<PhraseSlot> =
        List(size = MNEMONIC_SIZE_12) { PhraseSlot(pos = it, selected = it == 0) },
    val shuffledMnemonic: List<PhraseSlot> =
        List(size = MNEMONIC_SIZE_12) { PhraseSlot(pos = it) },
    val sprSelectedState: SRPSelectedState = SRPSelectedState.UNDONE,
) : UiState {
    val warningVisibility get() = sprSelectedState == SRPSelectedState.WRONG
    val btnCompleteEnable get() = sprSelectedState == SRPSelectedState.CORRECT
}

enum class SRPSelectedState {
    CORRECT, WRONG, UNDONE
}