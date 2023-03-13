package com.cj.bunnywallet.feature.importwallet

import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.reducer.UiState

sealed class ImportWalletEvent {
    data class SetPhraseAmountType(val phraseAmount: PhraseAmountType): ImportWalletEvent()
    data class SetPassword(val pwd: String): ImportWalletEvent()
    data class SetConfirmPassword(val pwd: String): ImportWalletEvent()
    data class UpdatePhrase(val index: Int, val phrase: String): ImportWalletEvent()
    object Import: ImportWalletEvent()
}

data class ImportWalletState(
    val phraseAmount: PhraseAmountType = PhraseAmountType.TWELVE_WORDS,
    val password: String = "",
    val passwordValid: Boolean = true,
    val confirmPassword: String = "",
    val confirmPasswordValid: Boolean = true
): UiState