package com.cj.bunnywallet.feature.startupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.datasource.local.BunnyPreferencesDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val dataStore: BunnyPreferencesDataStore,
) : ViewModel(), AppNavigator by appNavigator {

    init {
        checkStartDestination()
    }

    private fun checkStartDestination() {
        dataStore.getString(KEY_PWD)
            .onEach { pwd ->
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
            .launchIn(viewModelScope)
    }

}