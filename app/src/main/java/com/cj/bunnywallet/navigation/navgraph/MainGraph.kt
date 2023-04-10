package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.customcrypto.CustomCryptoScreen
import com.cj.bunnywallet.feature.walletsetup.WalletSetupScreen
import com.cj.bunnywallet.feature.walletsetup.WalletSetupViewModel
import com.cj.bunnywallet.feature.home.HomeScreen
import com.cj.bunnywallet.feature.home.HomeViewModel
import com.cj.bunnywallet.feature.importwallet.ImportWalletScreen
import com.cj.bunnywallet.feature.importwallet.ImportWalletViewModel
import com.cj.bunnywallet.feature.managecrypto.ManageCryptoScreen
import com.cj.bunnywallet.feature.managecrypto.ManageCryptoViewModel
import com.cj.bunnywallet.feature.startupscreen.StartupScreen
import com.cj.bunnywallet.feature.startupscreen.StartupViewModel
import com.cj.bunnywallet.feature.unlock.UnlockScreen
import com.cj.bunnywallet.feature.unlock.UnlockViewModel
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.startup() {
    composable(MainRoute.Startup.route) {
        hiltViewModel<StartupViewModel>()
        StartupScreen()
    }
}

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

fun NavGraphBuilder.home() {
    composable(MainRoute.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        HomeScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
        )
    }
}

fun NavGraphBuilder.manageCrypto() {
    composable(MainRoute.ManageCrypto.route) {

        val viewModel: ManageCryptoViewModel = hiltViewModel()

        ManageCryptoScreen(
            navEvent = viewModel::navigateTo
        )
    }
}

fun NavGraphBuilder.customCrypto() {
    composable(MainRoute.CustomCrypto.route) {

        CustomCryptoScreen()
    }
}
