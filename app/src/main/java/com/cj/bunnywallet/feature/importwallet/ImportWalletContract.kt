package com.cj.bunnywallet.feature.importwallet

import androidx.annotation.StringRes
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType
import com.cj.bunnywallet.reducer.UiState

sealed class ImportWalletEvent {
    data class SetPhraseAmountType(val phraseAmount: PhraseAmountType) : ImportWalletEvent()
    data class UpdatePhrase(val index: Int, val phrase: String) : ImportWalletEvent()
    object Import : ImportWalletEvent()
}

data class ImportWalletState(
    val phraseAmount: PhraseAmountType = PhraseAmountType.TWELVE_WORDS,
    val btnEnable: Boolean = BuildConfig.DEBUG,
    @StringRes val errMsg: Int? = null,
    val isLoading: Boolean = false
) : UiState