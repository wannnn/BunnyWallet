package com.cj.bunnywallet.feature.createwallet.confirmsrp

import com.cj.bunnywallet.MNEMONIC_SIZE_12
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot
import com.cj.bunnywallet.reducer.UiState

sealed interface ConfirmSRPContractEvent {
    object ToCreateWalletCompleted : ConfirmSRPContractEvent
}

data class ConfirmSRPContractState(
    val selectedMnemonic: MutableList<PhraseSlot> =
        MutableList(size = MNEMONIC_SIZE_12) { PhraseSlot(pos = it) },
    val shuffledMnemonic: MutableList<PhraseSlot> =
        MutableList(size = MNEMONIC_SIZE_12) { PhraseSlot(pos = it) },
) : UiState