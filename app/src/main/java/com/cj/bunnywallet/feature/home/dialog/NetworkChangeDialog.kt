package com.cj.bunnywallet.feature.home.dialog

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.common.DialogContainer
import com.cj.bunnywallet.feature.home.type.SupportNetwork
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.NoRippleInteractionSource

@Composable
fun NetworkChangeDialog(
    network: SupportNetwork,
    selectNetwork: (SupportNetwork) -> Unit,
    onDismiss: () -> Unit,
) {
    DialogContainer(onDismiss = onDismiss) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.network),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp,
                )

                CommonIconBtn(
                    icon = R.drawable.ic_clear,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    iconModifier = Modifier.size(20.dp),
                    onClick = onDismiss,
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(SupportNetwork.values().toList()) {
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)
                    NetworkItem(
                        network = it,
                        currentNetwork = network.name,
                        selectNetwork = { selectNetwork(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun NetworkItem(
    network: SupportNetwork,
    currentNetwork: String,
    selectNetwork: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 4.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                onClick = selectNetwork,
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = if (currentNetwork == network.name) {
                MaterialTheme.colorScheme.inverseSurface
            } else {
                Color.Transparent
            },
        )

        if (network == SupportNetwork.MAIN) {
            Image(
                painter = painterResource(id = R.drawable.ic_eth),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        } else {
            Text(
                text = network.initial,
                modifier = Modifier
                    .background(color = network.color, shape = CircleShape)
                    .size(24.dp)
                    .wrapContentHeight(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }

        Text(text = stringResource(id = network.networkName))
    }
}

@Preview(name = "Light Mode", showBackground = true, heightDp = 500)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 500,
)
@Composable
fun PreviewNetworkChangeDialog() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            NetworkChangeDialog(
                network = SupportNetwork.SEPOLIA,
                onDismiss = {},
                selectNetwork = {}
            )
        }
    }
}