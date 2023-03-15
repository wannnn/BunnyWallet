package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute

@Composable
fun SecureInfoView(
    onClick: () -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SecureWalletTitle(onClick)
        SecureSuggestions()
        CmnButton(
            text = stringResource(id = R.string.start),
            onClick = { navEvent(NavEvent.NavTo(CreateWalletRoute.ConfirmSRP.route)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSecureInfoView() {
    SecureInfoView({}, {})
}