package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R

@Composable
fun ProgressMessages() {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.92f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProgressMsg(R.string.create_password)
        ProgressMsg(R.string.secure_wallet)
        ProgressMsg(R.string.confirm_mnemonics)
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProgressMessages() {
    ProgressMessages()
}