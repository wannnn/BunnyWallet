package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.common.AppTopBar

@Composable
fun SecureWalletRoute() {
    SecureWalletScreen()
}

@Composable
fun SecureWalletScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar()

        Text(text = "SecureWalletScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWalletScreen() {
    SecureWalletScreen()
}