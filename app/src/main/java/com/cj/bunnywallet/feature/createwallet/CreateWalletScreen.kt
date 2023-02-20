package com.cj.bunnywallet.feature.createwallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.createwallet.component.progress.CreateWalletProgress
import com.cj.bunnywallet.feature.createwallet.component.title.CreatePwdTitle

@Composable
fun CreateWalletRoute() {
    CreateWalletScreen()
}

@Composable
fun CreateWalletScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar()
        CreateWalletProgress()
        CreatePwdTitle()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateWalletScreen() {
    CreateWalletScreen()
}