package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletEvent
import com.cj.bunnywallet.feature.createwallet.securewallet.SecureWalletStep

@Composable
fun ConfirmPwd(
    confirmErrMsgRes: Int?,
    confirmBtnEnable: Boolean,
    uiEvent: (SecureWalletEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.confirm_your_password),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
        )

        PwdField(confirmErrMsgRes = confirmErrMsgRes, uiEvent = uiEvent)

        CmnButton(
            text = stringResource(id = R.string.confirm),
            onClick = { uiEvent(SecureWalletEvent.UpdateStep(SecureWalletStep.GEN_SRP)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = confirmBtnEnable,
        )
    }
}

@Composable
fun PwdField(
    confirmErrMsgRes: Int? = null,
    uiEvent: (SecureWalletEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.confirm_your_hint),
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyLarge,
        )

        var confirmPwd by remember { mutableStateOf("") }

        PasswordTextField(
            passwordState = confirmPwd,
            passwordStateUpdate = {
                confirmPwd = it.trim()
                uiEvent(SecureWalletEvent.UpdateConfirmPwd(confirmPwd))
            },
            label = stringResource(id = R.string.confirm_password),
            imeAction = ImeAction.Done,
            errorMsg = confirmErrMsgRes?.let { stringResource(id = it) },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewConfirmPwd() {
    ConfirmPwd(
        confirmErrMsgRes = R.string.pwd_not_match,
        confirmBtnEnable = true,
        uiEvent = {},
    )
}