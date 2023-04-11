package com.cj.bunnywallet.navigation.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cj.bunnywallet.feature.createpwd.CreatePwdScreen
import com.cj.bunnywallet.feature.createpwd.CreatePwdViewModel
import com.cj.bunnywallet.feature.createwallet.CreateWalletScreen
import com.cj.bunnywallet.feature.createwallet.CreateWalletViewModel
import com.cj.bunnywallet.feature.importwallet.ImportWalletScreen
import com.cj.bunnywallet.feature.importwallet.ImportWalletViewModel
import com.cj.bunnywallet.feature.walletsetup.WalletSetupScreen
import com.cj.bunnywallet.feature.walletsetup.WalletSetupViewModel
import com.cj.bunnywallet.navigation.route.MainRoute

fun NavGraphBuilder.walletSetup() {
    composable(MainRoute.WalletSetup.route) {
        val viewModel: WalletSetupViewModel = hiltViewModel()

        WalletSetupScreen(viewModel::handleEvent)
    }
}

fun NavGraphBuilder.createPassword() {
    composable(MainRoute.CreatePassword.route) {
        val viewModel = hiltViewModel<CreatePwdViewModel>()
        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        CreatePwdScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
            navEvent = viewModel::navigateTo,
        )
    }
}

fun NavGraphBuilder.createWallet() {
    composable(MainRoute.CreateWallet.route) {
        val viewModel: CreateWalletViewModel = hiltViewModel()

        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

        CreateWalletScreen(
            uiState = uiState,
            uiEvent = viewModel::handleEvent,
            navEvent = viewModel::navigateTo
        )
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