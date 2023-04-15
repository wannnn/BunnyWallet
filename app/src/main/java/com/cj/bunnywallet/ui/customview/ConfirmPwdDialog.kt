package com.cj.bunnywallet.ui.customview

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.DialogContainer
import com.cj.bunnywallet.feature.common.PasswordTailIconTextField
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun ConfirmPwdDialog(
    onDismiss: (Boolean) -> Unit,
    correctPwd: String,
) {
    DialogContainer(onDismiss = { onDismiss(false) }) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Confirm password",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            var pwd by remember { mutableStateOf("") }
            var errMsg by remember { mutableStateOf(0) }

            PasswordTailIconTextField(
                passwordState = pwd,
                passwordStateUpdate = {
                    pwd = it
                    errMsg = 0
                },
                label = stringResource(id = R.string.password),
                imeAction = ImeAction.Done,
                errorMsg = if (errMsg == 0) null else stringResource(id = errMsg)
            )

            CmnButton(
                text = stringResource(id = R.string.confirm),
                onClick = {
                    if (pwd == correctPwd) {
                        onDismiss(true)
                    } else {
                        errMsg = R.string.pwd_not_match
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = pwd.isNotBlank()
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ConfirmPwdDialog(onDismiss = {}, correctPwd = "")
        }
    }
}