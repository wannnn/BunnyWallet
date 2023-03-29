package com.cj.bunnywallet.feature.walletsetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WalletSetupScreen(uiEvent: (WalletSetupEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { uiEvent(WalletSetupEvent.CreateWallet) }) {
            Text(text = "To Create Wallet Screen")
        }

        Button(onClick = { uiEvent(WalletSetupEvent.ImportWallet) }) {
            Text(text = "To Import Wallet Screen")
        }
    }
}

@Preview
@Composable
fun PreviewWalletSetupScreen() {
    WalletSetupScreen(uiEvent = {})
}