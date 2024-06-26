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
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletEvent
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletStep

@Composable
fun SecureInfoView(uiEvent: (SecureWalletEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SecureWalletTitle(onDialogClick = { uiEvent(SecureWalletEvent.HandleDialog(it)) })
        SecureSuggestions()
        CmnButton(
            text = stringResource(id = R.string.start),
            onClick = { uiEvent(SecureWalletEvent.UpdateStep(SecureWalletStep.CONFIRM_PWD)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSecureInfoView() {
    SecureInfoView(uiEvent = {})
}