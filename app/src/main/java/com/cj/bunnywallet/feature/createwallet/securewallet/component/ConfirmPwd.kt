package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.PasswordTextField

@Composable
fun ConfirmPwd() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.confirm_your_password),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
        )

        PwdField()

        CmnButton(
            text = stringResource(id = R.string.confirm),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun PwdField() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.confirm_your_hint),
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyLarge,
        )

        PasswordTextField(
            passwordState = "",
            passwordStateUpdate = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmPwd() {
    ConfirmPwd()
}

@Preview(showBackground = true)
@Composable
fun PreviewPwdField() {
    PwdField()
}