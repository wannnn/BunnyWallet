package com.cj.bunnywallet.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cj.bunnywallet.feature.managewallet.edit.EditWalletScreen
import com.cj.bunnywallet.feature.managewallet.manage.ManageWalletScreen
import com.cj.bunnywallet.navigation.route.ManageWalletRoute

fun NavGraphBuilder.manageWalletGraph() {
    navigation(
        startDestination = ManageWalletRoute.ManageWallet.route,
        route = ManageWalletRoute.ManageWalletGraph.route,
    ) {
        composable(ManageWalletRoute.ManageWallet.route) {
            ManageWalletScreen()
        }

        composable(ManageWalletRoute.EditWallet.route) {
            EditWalletScreen()
        }
    }
}