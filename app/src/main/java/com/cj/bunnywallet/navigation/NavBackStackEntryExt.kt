package com.cj.bunnywallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

/** To get parent ViewModel **/
@Composable
inline fun <reified PVM : ViewModel> NavBackStackEntry.getParentViewModel(
    navController: NavHostController,
): PVM = hiltViewModel(
    remember(this) { navController.getBackStackEntry(destination.parent!!.id) },
)
