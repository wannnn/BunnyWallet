package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R

@Composable
fun ProgressMessages() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProgressMsg(msgRes = R.string.create_password)
        ProgressMsg(msgRes = R.string.secure_wallet)
        ProgressMsg(msgRes = R.string.confirm_mnemonics)
    }
}

@Composable
private fun ProgressMsg(msgRes: Int) {
    Text(
        text = stringResource(id = msgRes),
        color = MaterialTheme.colorScheme.primary,
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        lineHeight = 10.sp
    )
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProgressMessages() {
    ProgressMessages()
}