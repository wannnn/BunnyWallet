package com.cj.bunnywallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.cj.bunnywallet.app.rememberAppState
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.navigation.AppNavHost
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    @Inject
    lateinit var dataStore: BunnyDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore.getString(KEY_PWD).onEach {

            val hasPwd = it.isNotBlank()
            val startDestination = if (hasPwd) MainRoute.Unlock.route else MainRoute.Entrance.route

            setContent {
                BunnyWalletTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                            .imePadding(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val appState = rememberAppState(appNavigator = appNavigator)

                        AppNavHost(
                            navController = appState.navController,
                            appNavigator = appState.appNavigator,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }
}