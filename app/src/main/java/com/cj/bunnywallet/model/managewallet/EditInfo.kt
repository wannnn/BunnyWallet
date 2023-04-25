package com.cj.bunnywallet.model.managewallet

import com.cj.bunnywallet.feature.managewallet.edit.type.EditType

data class EditInfo(
    val walletId: String,
    val address: String,
    val name: String,
    val type: EditType,
)