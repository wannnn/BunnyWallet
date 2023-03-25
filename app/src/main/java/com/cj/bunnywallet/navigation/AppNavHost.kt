package com.cj.bunnywallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cj.bunnywallet.navigation.navgraph.createWalletGraph
import com.cj.bunnywallet.navigation.navgraph.entrance
import com.cj.bunnywallet.navigation.navgraph.home
import com.cj.bunnywallet.navigation.navgraph.importWallet
import com.cj.bunnywallet.navigation.navgraph.unlock
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavHost(
    navController: NavHostController,
    appNavigator: AppNavigator,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(navController) {
        appNavigator.destinationFlow
            .onEach { navController.handleNavEvent(it) }
            .launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        unlock()
        entrance()
        createWalletGraph()
        importWallet()
        home()
    }
}

private fun NavHostController.handleNavEvent(navEvent: NavEvent) {
    when (navEvent) {
        is NavEvent.NavBack -> navigateUp()
        is NavEvent.NavTo -> navigate(navEvent.route, navEvent.navOptions)
    }
}