package com.cj.bunnywallet.feature.createwallet.confirmsrp.model

data class PhraseSlot(
    val pos: Int,
    val phrase: String = "",
    val selected: Boolean = false,
) {
//    val borderState get() = when {
//
//        phrase.isNotBlank() -> {
//            // 藍色實線
//            ""
//        }
//
//        selected -> {
//            // 藍色虛線
//            ""
//        }
//
//        else -> {
//            // 灰色虛線
//            ""
//        }
//
//    }
}
