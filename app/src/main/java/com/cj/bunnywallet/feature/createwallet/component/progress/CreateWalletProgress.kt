package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep

@Composable
fun CreateWalletProgress(step: CreateWalletStep) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProgressGraph(step)
        ProgressMessages()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateWalletProgress() {
    CreateWalletProgress(CreateWalletStep.DONE)
}