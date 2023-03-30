package com.cj.bunnywallet.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.home.component.HomeTopBar
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun HomeScreen(
    uiState: HomeState
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()

        Text(text = "ETH balance: ${uiState.balance}")
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(
                uiState = HomeState(),
            )
        }
    }
}