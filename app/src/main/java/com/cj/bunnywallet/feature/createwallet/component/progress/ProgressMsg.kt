package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.Purple40

@Composable
fun ProgressMsg(msgRes: Int) {
    Text(
        text = stringResource(id = msgRes),
        color = Purple40,
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        lineHeight = 10.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressMsg() {
    ProgressMsg(R.string.confirm_mnemonics)
}