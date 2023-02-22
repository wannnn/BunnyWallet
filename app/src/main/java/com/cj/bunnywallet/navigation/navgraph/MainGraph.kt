package com.cj.bunnywallet.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.entrance.EntranceRoute
import com.cj.bunnywallet.feature.importwallet.ImportWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.mainGraph() {
    entrance()
    importWallet()
}

private fun NavGraphBuilder.entrance() {
    composable(MainRoute.Entrance.route) { EntranceRoute() }
}

private fun NavGraphBuilder.importWallet() {
    composable(MainRoute.ImportWallet.route) { ImportWalletRoute() }
}
