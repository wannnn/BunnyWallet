package com.cj.bunnywallet.feature.managewallet.manage.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.cj.bunnywallet.R

enum class ManageType(@DrawableRes val icon: Int, @StringRes val typeName: Int) {
    ADD_WALLET(icon = R.drawable.ic_add, typeName = R.string.add_wallet),
    EDIT_WALLET(icon = R.drawable.ic_edit_note, typeName = R.string.edit_wallet),
}