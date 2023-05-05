package com.cj.bunnywallet.feature.managecrypto.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.proto.wallet.Crypto
import com.cj.bunnywallet.proto.wallet.crypto
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.Gray500

@Composable
fun CryptoItem(
    crypto: Crypto,
    @DrawableRes rightIcon: Int,
    iconTint: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        AsyncImage(
            model = crypto.logo,
            contentDescription = null,
            modifier = Modifier
                .weight(weight = 1f)
                .aspectRatio(ratio = 1f),
            error = painterResource(id = R.drawable.ic_crypto)
        )

        Column(
            modifier = Modifier
                .weight(weight = 7f)
                .padding(horizontal = 10.dp)
        ) {
            Text(text = crypto.symbol)
            Text(
                text = crypto.name,
                style = MaterialTheme.typography.bodySmall,
                color = Gray500
            )
        }

        CommonIconBtn(
            icon = rightIcon,
            modifier = Modifier.weight(weight = 1f),
            tint = iconTint,
            onClick = {}
        )
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewCryptoItem() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CryptoItem(
                crypto = crypto {
                    logo = "https://static.alchemyapi.io/images/assets/4705.png"
                    name = "PAX Gold"
                    symbol = "PAXG"
                },
                rightIcon = R.drawable.ic_add_circle_outline,
                iconTint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}