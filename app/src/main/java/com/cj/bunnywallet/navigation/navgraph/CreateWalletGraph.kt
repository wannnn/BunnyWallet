package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.cj.bunnywallet.feature.createwallet.completed.CompletedScreen
import com.cj.bunnywallet.feature.createwallet.completed.CompletedViewModel
import com.cj.bunnywallet.feature.createwallet.confirmsrp.ConfirmSRPScreen
import com.cj.bunnywallet.feature.createwallet.confirmsrp.ConfirmSRPViewModel
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletScreen
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletViewModel
import com.cj.bunnywallet.navigation.route.CreateWalletRoute

fun NavGraphBuilder.createWalletGraph() {
    navigation(
        startDestination = CreateWalletRoute.SecureWallet.route,
        route = CreateWalletRoute.CreateWallet.route,
    ) {
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
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            ConfirmSRPScreen(
                uiState = uiState,
                uiEvent = viewModel::handleEvent,
                navEvent = viewModel::navigateTo,
            )
        }

        composable(CreateWalletRoute.Completed.route) {
            val viewModel = hiltViewModel<CompletedViewModel>()
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            CompletedScreen(
                uiState = uiState,
                uiEvent = viewModel::handleEvent,
            )
        }
    }
}