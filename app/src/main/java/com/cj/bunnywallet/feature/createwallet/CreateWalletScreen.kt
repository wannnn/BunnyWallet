package com.cj.bunnywallet.feature.createwallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

@Composable
fun CreateWalletRoute() {
    CreateWalletScreen()
}

@Composable
fun CreateWalletScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "CreateWalletScreen")
    }
}

@Preview
@Composable
fun PreviewCreateWalletScreen() {
    CreateWalletScreen()
}