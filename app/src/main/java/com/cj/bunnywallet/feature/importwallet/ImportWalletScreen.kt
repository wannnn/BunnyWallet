package com.cj.bunnywallet.feature.importwallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImportWalletRoute() {
    ImportWalletScreen()
}

@Composable
fun ImportWalletScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "ImportWalletScreen")
    }
}

@Preview
@Composable
fun PreviewImportWalletScreen() {
    ImportWalletScreen()
}