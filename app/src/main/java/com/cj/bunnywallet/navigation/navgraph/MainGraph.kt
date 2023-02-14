package com.cj.bunnywallet.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.createwallet.CreateWalletRoute
import com.cj.bunnywallet.feature.entrance.EntranceRoute
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.mainGraph() {
    addEntrance()
    addCreateWallet()
}

private fun NavGraphBuilder.addEntrance() {
    composable(MainRoute.Entrance.route) { EntranceRoute(it) }
}

private fun NavGraphBuilder.addCreateWallet() {
    composable(MainRoute.CreateWallet.route) { CreateWalletRoute() }
}
