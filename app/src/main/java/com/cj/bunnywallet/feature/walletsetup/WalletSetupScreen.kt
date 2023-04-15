package com.cj.bunnywallet.feature.walletsetup

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.NoRippleInteractionSource

@Composable
fun WalletSetupScreen(uiEvent: (WalletSetupEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_polar_bear),
            contentDescription = null,
        )

        SetupAction(
            action = R.string.import_wallet,
            description = R.string.import_wallet_description,
            icon = R.drawable.ic_wallet,
            onClick = { uiEvent(WalletSetupEvent.ImportWallet) },
            modifier = Modifier.padding(top = 32.dp)
        )

        SetupAction(
            action = R.string.create_wallet,
            description = R.string.create_wallet_description,
            icon = R.drawable.ic_add,
            onClick = { uiEvent(WalletSetupEvent.CreateWallet) },
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun SetupAction(
    @StringRes action: Int,
    @StringRes description: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth(fraction = .8f)
            .defaultMinSize(minHeight = 88.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(vertical = 16.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.weight(weight = 1f),
            tint = MaterialTheme.colorScheme.primary,
        )

        Column(
            modifier = Modifier.weight(weight = 4f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(id = action),
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = stringResource(id = description),
                fontSize = 14.sp,
                lineHeight = 16.sp,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier
                .weight(weight = 1f)
                .size(20.dp),
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewWalletSetupScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WalletSetupScreen(uiEvent = {})
        }
    }
}