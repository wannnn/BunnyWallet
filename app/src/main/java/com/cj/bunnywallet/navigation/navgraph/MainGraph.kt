package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.entrance.EntranceScreen
import com.cj.bunnywallet.feature.entrance.EntranceViewModel
import com.cj.bunnywallet.feature.home.HomeScreen
import com.cj.bunnywallet.feature.home.HomeViewModel
import com.cj.bunnywallet.feature.importwallet.ImportWalletScreen
import com.cj.bunnywallet.feature.importwallet.ImportWalletViewModel
import com.cj.bunnywallet.feature.unlock.UnlockScreen
import com.cj.bunnywallet.feature.unlock.UnlockViewModel
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.unlock() {
    composable(MainRoute.Unlock.route) {
        val viewModel: UnlockViewModel = hiltViewModel()

        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        UnlockScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
        )
    }
}

fun NavGraphBuilder.entrance() {
    composable(MainRoute.Entrance.route) {
        val viewModel: EntranceViewModel = hiltViewModel()

        EntranceScreen(viewModel::navigateTo)
    }
}

fun NavGraphBuilder.importWallet() {
    composable(MainRoute.ImportWallet.route) {
        val viewModel: ImportWalletViewModel = hiltViewModel()

        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        ImportWalletScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
            navEvent = viewModel::navigateTo
        )
    }
}

fun NavGraphBuilder.home() {
    composable(MainRoute.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()

        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        HomeScreen(
            uiState = uiState
        )
    }
}
