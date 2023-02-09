package com.cj.bunnywallet.navigation.route

sealed interface MainRoute : Routes {

    object ScreenA : MainRoute {
        override val route: String = "screen_a"
    }

    object ScreenB : MainRoute {
        override val route: String = "screen_b"
    }
}
