package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute

@Composable
fun CreateWalletButton(navEvent: (NavEvent) -> Unit) {
    CmnButton(
        text = stringResource(id = R.string.create_password),
        onClick = { navEvent(NavEvent.NavTo(CreateWalletRoute.SecureWallet.route)) },
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
    )
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreateWalletButton() {
    CreateWalletButton {}
}