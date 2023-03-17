package com.cj.bunnywallet.feature.entrance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute

@Composable
fun EntranceScreen(navEvent: (NavEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { navEvent(NavEvent.NavTo(MainRoute.CreateWallet.route)) }) {
            Text(text = "To Create Wallet Screen")
        }

        Button(onClick = { navEvent(NavEvent.NavTo(MainRoute.ImportWallet.route)) }) {
            Text(text = "To Import Wallet Screen")
        }

        Button(onClick = { navEvent(NavEvent.NavTo(MainRoute.Home.route)) }) {
            Text(text = "To Home Screen")
        }
    }
}

@Preview
@Composable
fun PreviewEntranceScreen() {
    EntranceScreen {}
}