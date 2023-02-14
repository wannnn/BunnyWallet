package com.cj.bunnywallet.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cj.bunnywallet.navigation.AppNavigator

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    appNavigator: AppNavigator,
) = remember(navController, appNavigator) {
    AppState(navController, appNavigator)
}

@Stable
class AppState(
    val navController: NavHostController,
    val appNavigator: AppNavigator,
) {
    // to keep app state
}