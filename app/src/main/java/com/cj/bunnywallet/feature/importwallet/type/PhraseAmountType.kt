package com.cj.bunnywallet.feature.importwallet.type

import androidx.annotation.StringRes
import com.cj.bunnywallet.R

enum class PhraseAmountType(@StringRes val txtId: Int, val amount: Int) {
    TWELVE_WORDS(R.string.twelve_words, amount = 12),
    FIFTEEN_WORDS(R.string.fifteen_words, amount = 15),
    EIGHTEEN_WORDS(R.string.eighteen_words, amount = 18),
    TWENTY_ONE_WORDS(R.string.twenty_one_words, amount = 21),
    TWENTY_FOUR_WORDS(R.string.twenty_four_words, amount = 24),
}