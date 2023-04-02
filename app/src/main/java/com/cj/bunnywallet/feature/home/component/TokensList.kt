package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.home.HomeState
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun TokensList(uiState: HomeState) {
    var balanceVisibility by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            AssetsInfo(
                balance = uiState.balance,
                balanceVisibility = balanceVisibility,
                onVisibilityChange = { balanceVisibility = balanceVisibility.not() }
            )
        }

        items(count = 10) {
            TokenCard()
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewTokensList() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TokensList(HomeState(balance = "123.456"))
        }
    }
}