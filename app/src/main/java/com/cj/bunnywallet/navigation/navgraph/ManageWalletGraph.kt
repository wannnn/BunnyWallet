package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.cj.bunnywallet.feature.createpwd.CreatePwdViewModel
import com.cj.bunnywallet.feature.managewallet.edit.EditWalletScreen
import com.cj.bunnywallet.feature.managewallet.manage.ManageWalletScreen
import com.cj.bunnywallet.feature.managewallet.manage.ManageWalletViewModel
import com.cj.bunnywallet.navigation.route.ManageWalletRoute

fun NavGraphBuilder.manageWalletGraph() {
    navigation(
        startDestination = ManageWalletRoute.ManageWallet.route,
        route = ManageWalletRoute.ManageWalletGraph.route,
    ) {
        composable(ManageWalletRoute.ManageWallet.route) {
            val viewModel = hiltViewModel<ManageWalletViewModel>()
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            ManageWalletScreen(
                uiState = uiState,
                uiEvent = viewModel::handleEvent,
            )
        }

        composable(ManageWalletRoute.EditWallet.route) {
            EditWalletScreen()
        }
    }
}