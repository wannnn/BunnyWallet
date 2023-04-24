package com.cj.bunnywallet

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cj.bunnywallet.app.rememberAppState
import com.cj.bunnywallet.navigation.AppNavHost
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.utils.SnackBarManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    @Inject
    lateinit var snackBarManager: SnackBarManager

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BunnyWalletTheme {

                val snackbarHostState = remember { SnackbarHostState() }
                val appState = rememberAppState(appNavigator = appNavigator)

                LaunchedEffect(snackbarHostState) {
                    snackBarManager.messages.collect { currentMessages ->
                        snackbarHostState.showSnackbar(currentMessages)
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                        .systemBarsPadding()
                        .navigationBarsPadding()
                        .imePadding(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) {
                    AppNavHost(
                        navController = appState.navController,
                        appNavigator = appState.appNavigator,
                    )
                }
            }
        }
    }
}