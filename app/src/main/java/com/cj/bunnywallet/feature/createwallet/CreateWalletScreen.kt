package com.cj.bunnywallet.feature.createwallet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.createwallet.component.RevealSRPView
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun CreateWalletScreen(
    uiState: CreateWalletState,
    uiEvent: (CreateWalletEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(onBackClicked = { navEvent(NavEvent.NavBack) })

        RevealSRPView(
            mnemonic = uiState.mnemonic,
            uiEvent = uiEvent,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewCreateWalletScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CreateWalletScreen(
                uiState = CreateWalletState(),
                uiEvent = {},
                navEvent = {},
            )
        }
    }
}