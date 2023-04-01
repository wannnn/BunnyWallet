package com.cj.bunnywallet.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.home.component.AccountInfo
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.home.component.BalanceInfo
import com.cj.bunnywallet.feature.home.component.HomeTopBar
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun HomeScreen(
    uiState: HomeState
) {
    var balanceVisibility by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()

        AccountInfo()

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            BalanceInfo(
                balance = uiState.balance,
                balanceVisibility = balanceVisibility,
                onVisibilityChange = { balanceVisibility = balanceVisibility.not() }
            )
        }
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