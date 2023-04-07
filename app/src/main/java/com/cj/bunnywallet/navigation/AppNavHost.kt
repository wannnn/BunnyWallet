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
import com.cj.bunnywallet.navigation.navgraph.manageWalletGraph
import com.cj.bunnywallet.navigation.navgraph.manageCrypto
import com.cj.bunnywallet.navigation.navgraph.startup
import com.cj.bunnywallet.navigation.navgraph.unlock
import com.cj.bunnywallet.navigation.route.MainRoute
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavHost(
    navController: NavHostController,
    appNavigator: AppNavigator,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(navController) {
        appNavigator.destinationFlow
            .onEach { navController.handleNavEvent(it) }
            .launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = MainRoute.Startup.route,
        modifier = modifier,
    ) {
        startup()
        unlock()
        entrance()
        createWalletGraph()
        importWallet()
        home()
        manageWalletGraph()
        manageCrypto()
    }
}

private fun NavHostController.handleNavEvent(navEvent: NavEvent) {
    when (navEvent) {
        is NavEvent.NavBack -> navigateUp()

        is NavEvent.NavTo -> navigate(navEvent.route, navEvent.navOptions)

        is NavEvent.PopBackTo -> popBackStack(
            route = navEvent.route,
            inclusive = navEvent.inclusive,
            saveState = navEvent.saveState,
        )
    }
}