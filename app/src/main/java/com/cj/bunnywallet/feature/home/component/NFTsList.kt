package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun NFTsList() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "~~ Coming Soon ~~", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewNFTsList() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            NFTsList()
        }
    }
}