package com.cj.bunnywallet.feature.managecrypto.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.Gray300
import com.cj.bunnywallet.ui.theme.Gray500

@Composable
fun CryptoItem(hasAdded: Boolean) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_eth),
            contentDescription = null,
            modifier = Modifier
                .weight(weight = 1f)
                .aspectRatio(ratio = 1f)
        )

        Column(
            modifier = Modifier
                .weight(weight = 7f)
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "ETH")
            Text(
                text = "Ethereum",
                style = MaterialTheme.typography.bodySmall,
                color = Gray500
            )
        }

        CommonIconBtn(
            icon = if (hasAdded) {
                R.drawable.ic_remove_circle_outline
            } else {
                R.drawable.ic_add_circle_outline
            },
            modifier = Modifier.weight(weight = 1f),
            tint = if (hasAdded) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.tertiary
            },
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
            CryptoItem(hasAdded = false)
        }
    }
}