package com.cj.bunnywallet.feature.createwallet.confirmsrp.model

data class PhraseSlot(
    val pos: Int,
    val phrase: String = "",
    val selected: Boolean = false,
)
