package com.cj.bunnywallet.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cj.bunnywallet.feature.createwallet.CreateWalletViewModel
import com.cj.bunnywallet.feature.createwallet.completed.CreateWalletCompletedRoute
import com.cj.bunnywallet.feature.createwallet.confirmsrp.ConfirmSRPRoute
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdRoute
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletRoute
import com.cj.bunnywallet.navigation.getParentViewModel
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.createWalletGraph(navController: NavHostController) {
    navigation(
        startDestination = CreateWalletRoute.CreatePassword.route,
        route = MainRoute.CreateWallet.route,
    ) {
        composable(CreateWalletRoute.CreatePassword.route) {
            val parentViewModel = it.getParentViewModel<CreateWalletViewModel>(navController)
            CreatePwdRoute(parentViewModel)
        }

        composable(CreateWalletRoute.SecureWallet.route) {
            val parentViewModel = it.getParentViewModel<CreateWalletViewModel>(navController)
            SecureWalletRoute()
        }

        composable(CreateWalletRoute.ConfirmSRP.route) { ConfirmSRPRoute() }

        composable(CreateWalletRoute.CreateWalletCompleted.route) { CreateWalletCompletedRoute() }
    }
}