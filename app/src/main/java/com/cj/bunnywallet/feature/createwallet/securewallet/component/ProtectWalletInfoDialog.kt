package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton

@Composable
fun ProtectWalletInfoDialog() {
    val annoInfo = buildAnnotatedString {
        append(stringResource(id = R.string.protect_your_wallet_info_1).plus(" "))
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(stringResource(id = R.string.protect_your_wallet_info_2))
        }
    }

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(all = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.protect_your_wallet),
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = annoInfo,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )

                CmnButton(
                    text = stringResource(id = R.string.close),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProtectWalletInfoDialog() {
    ProtectWalletInfoDialog()
}