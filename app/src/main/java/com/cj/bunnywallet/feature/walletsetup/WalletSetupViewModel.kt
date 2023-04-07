package com.cj.bunnywallet.feature.walletsetup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WalletSetupViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val dataStore: BunnyDataStore,
) : ViewModel(), AppNavigator by appNavigator {

    private var hasPwd = false

    init {
        observePwd()
    }

    fun handleEvent(e: WalletSetupEvent) {
        val route = when (e) {
            WalletSetupEvent.CreateWallet -> if (hasPwd) {
                CreateWalletRoute.SecureWallet.route
            } else {
                CreateWalletRoute.CreateWallet.route
            }

            WalletSetupEvent.ImportWallet -> MainRoute.ImportWallet.route
        }
        navigateTo(NavEvent.NavTo(route))
    }

    private fun observePwd() {
        dataStore.getString(key = KEY_PWD, observe = true)
            .onEach { pwd -> hasPwd = pwd.isNotBlank() }
            .launchIn(viewModelScope)
    }
}