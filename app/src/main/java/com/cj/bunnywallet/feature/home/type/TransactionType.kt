package com.cj.bunnywallet.feature.home.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cj.bunnywallet.R

enum class TransactionType(@DrawableRes val icon: Int, @StringRes val txt: Int) {
    SEND(R.drawable.ic_send, R.string.send),
    RECEIVE(R.drawable.ic_receive, R.string.receive),
    BUY(R.drawable.ic_card, R.string.buy),
    HISTORY(R.drawable.ic_history, R.string.history),
}