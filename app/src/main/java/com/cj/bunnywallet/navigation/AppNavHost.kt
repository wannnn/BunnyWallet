package com.cj.bunnywallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cj.bunnywallet.navigation.navgraph.mainGraph
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
        startDestination = MainRoute.Entrance.route,
        modifier = modifier,
    ) {
        mainGraph()
    }
}

private fun NavHostController.handleNavEvent(navEvent: NavEvent) {
    when (navEvent) {
        is NavEvent.NavBack -> navigateUp()
        is NavEvent.NavTo -> navigate(navEvent.route, navEvent.navOptions)
    }
}