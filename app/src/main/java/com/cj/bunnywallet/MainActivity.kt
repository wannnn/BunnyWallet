package com.cj.bunnywallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.app.rememberAppState
import com.cj.bunnywallet.navigation.AppNavHost
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    val cm = CryptoManager()
    val msg = "test crypto message"
    val msg2 = "test crypto message 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    )
                }
            }
        }

        println("raw: $msg")
        val ENBA = cm.encrypt(msg.encodeToByteArray())
        println("ENBA: $ENBA")
        ENBA?.let {
            val DEBA = cm.decrypt(ENBA)
            println("DEBA: $DEBA")
            println("DEBA to String: ${DEBA?.decodeToString()}")
        }

        println("raw 2: $msg2")
        val ENBA2 = cm.encrypt(msg2.encodeToByteArray())
        println("ENBA2: $ENBA2")
        ENBA2?.let {
            val DEBA2 = cm.decrypt(ENBA2)
            println("DEBA2: $DEBA2")
            println("DEBA2 to String: ${DEBA2?.decodeToString()}")
        }
    }
}