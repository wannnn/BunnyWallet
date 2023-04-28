package com.cj.bunnywallet.feature.startupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_NETWORK
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.datasource.local.BunnyPreferencesDataStore
import com.cj.bunnywallet.feature.home.type.SupportNetwork
import com.cj.bunnywallet.helper.ApiHostHelper
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val apiHostHelper: ApiHostHelper,
    private val preferenceDS: BunnyPreferencesDataStore,
) : ViewModel(), AppNavigator by appNavigator {

    init {
        start()
    }

    private fun start() {
        combine(
            preferenceDS.getString(KEY_NETWORK),
            preferenceDS.getString(KEY_PWD),
        ) { network, pwd ->
            handleNetwork(network)
            handlePwd(pwd) // must be the last step, this will process navigate
        }
            .launchIn(viewModelScope)
    }

    private fun handleNetwork(network: String) {
        apiHostHelper.updateUrl(
            when (network) {
                SupportNetwork.MAIN.name -> SupportNetwork.MAIN
                SupportNetwork.GOERLI.name -> SupportNetwork.GOERLI
                else -> SupportNetwork.SEPOLIA
            }
        )
    }

    private fun handlePwd(pwd: String) {
        val navEvent = NavEvent.NavTo(
            route = if (pwd.isBlank()) {
                MainRoute.CreatePassword.route
            } else {
                MainRoute.Unlock.route
            },
            navOptions = NavOptions.Builder()
                .setPopUpTo(route = MainRoute.Startup.route, inclusive = true)
                .build(),
        )
        navigateTo(navEvent)
    }

}