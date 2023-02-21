package com.cj.bunnywallet.feature.createwallet.component.createbtn

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton

@Composable
fun CreateWalletButton() {
    CmnButton(
        text = stringResource(id = R.string.create_password),
        onClick = {},
        modifier = Modifier.fillMaxWidth(fraction = 0.8f),
        enabled = true,
    )
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreateWalletButton() {
    CreateWalletButton()
}