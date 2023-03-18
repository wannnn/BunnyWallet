package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.entrance.EntranceScreen
import com.cj.bunnywallet.feature.entrance.EntranceViewModel
import com.cj.bunnywallet.feature.home.HomeScreen
import com.cj.bunnywallet.feature.importwallet.ImportWalletScreen
import com.cj.bunnywallet.feature.importwallet.ImportWalletViewModel
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.mainGraph() {
    entrance()
    importWallet()
    home()
}

private fun NavGraphBuilder.entrance() {
    composable(MainRoute.Entrance.route) {

        val viewModel: EntranceViewModel = hiltViewModel()

        EntranceScreen(viewModel::navigateTo)
    }
}

private fun NavGraphBuilder.importWallet() {
    composable(MainRoute.ImportWallet.route) {

        val viewModel: ImportWalletViewModel = hiltViewModel()

        val uiState by viewModel.uiStateFlow.collectAsState()

        ImportWalletScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
            navEvent = viewModel::navigateTo
        )
    }
}

private fun NavGraphBuilder.home() {
    composable(MainRoute.Home.route) {
        HomeScreen()
    }
}
