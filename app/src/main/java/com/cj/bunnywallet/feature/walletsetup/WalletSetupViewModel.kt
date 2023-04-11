package com.cj.bunnywallet.feature.walletsetup

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletSetupViewModel @Inject constructor(
    appNavigator: AppNavigator,
) : ViewModel(), AppNavigator by appNavigator {

    fun handleEvent(e: WalletSetupEvent) {
        val route = when (e) {
            WalletSetupEvent.CreateWallet -> MainRoute.CreateWallet.route
            WalletSetupEvent.ImportWallet -> MainRoute.ImportWallet.route
        }
        navigateTo(NavEvent.NavTo(route))
    }
}