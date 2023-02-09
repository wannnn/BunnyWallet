package com.cj.bunnywallet.navigation.navgraph

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.screen.ScreenA
import com.cj.bunnywallet.screen.ScreenB

fun NavGraphBuilder.mainGraph(navTo: (NavBackStackEntry, NavEvent) -> Unit) {
    addScreenA(navTo)
    addScreenB()
}

private fun NavGraphBuilder.addScreenA(navTo: (NavBackStackEntry, NavEvent) -> Unit) {
    composable(MainRoute.ScreenA.route) {
        ScreenA(navEvent = { event -> navTo(it, event) })
    }
}

private fun NavGraphBuilder.addScreenB() {
    composable(MainRoute.ScreenB.route) { ScreenB() }
}