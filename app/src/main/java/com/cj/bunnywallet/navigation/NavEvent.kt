package com.cj.bunnywallet.navigation

import androidx.navigation.NavOptions

sealed class NavEvent {

    object NavBack : NavEvent()

    data class NavTo(
        val route: String,
        val navOptions: NavOptions? = null,
    ) : NavEvent()
}