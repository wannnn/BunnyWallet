package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.entrance.EntranceRoute
import com.cj.bunnywallet.feature.importwallet.ImportWalletRoute
import com.cj.bunnywallet.feature.importwallet.ImportWalletViewModel
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.mainGraph() {
    entrance()
    importWallet()
}

private fun NavGraphBuilder.entrance() {
    composable(MainRoute.Entrance.route) { EntranceRoute() }
}

private fun NavGraphBuilder.importWallet() {
    composable(MainRoute.ImportWallet.route) {

        val viewModel: ImportWalletViewModel = hiltViewModel()

        val uiState by viewModel.uiState.collectAsState()

        ImportWalletRoute(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
        )
    }
}
