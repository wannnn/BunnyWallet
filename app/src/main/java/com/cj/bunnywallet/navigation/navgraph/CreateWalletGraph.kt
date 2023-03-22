package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.cj.bunnywallet.feature.createwallet.completed.CreateWalletCompletedRoute
import com.cj.bunnywallet.feature.createwallet.confirmsrp.ConfirmSRPRoute
import com.cj.bunnywallet.feature.createwallet.confirmsrp.ConfirmSRPViewModel
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdScreen
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdViewModel
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletScreen
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletViewModel
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.createWalletGraph() {
    navigation(
        startDestination = CreateWalletRoute.CreatePassword.route,
        route = MainRoute.CreateWallet.route,
    ) {
        composable(CreateWalletRoute.CreatePassword.route) {
            val viewModel = hiltViewModel<CreatePwdViewModel>()
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            CreatePwdScreen(
                uiState = uiState,
                uiEvent = viewModel::handleEvent,
                navEvent = viewModel::navigateTo,
            )
        }

        composable(CreateWalletRoute.SecureWallet.route) {
            val viewModel = hiltViewModel<SecureWalletViewModel>()
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            SecureWalletScreen(
                uiState = uiState,
                uiEvent = viewModel::handleEvent,
                navEvent = viewModel::navigateTo,
            )
        }

        composable(
            CreateWalletRoute.ConfirmSRP.route,
            arguments = listOf(
                navArgument(CreateWalletRoute.ConfirmSRP.SRP) { type = NavType.StringType },
            ),
        ) {
            val viewModel = hiltViewModel<ConfirmSRPViewModel>()
            ConfirmSRPRoute()
        }

        composable(CreateWalletRoute.CreateWalletCompleted.route) { CreateWalletCompletedRoute() }
    }
}