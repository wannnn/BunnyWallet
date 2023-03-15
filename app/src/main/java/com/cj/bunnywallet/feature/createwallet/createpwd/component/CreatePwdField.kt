package com.cj.bunnywallet.feature.createwallet.createpwd.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdEvent
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdState

@Composable
fun CreatePwdField(
    uiState: CreatePwdState,
    uiEvent: (CreatePwdEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        PwdVisibilityIcon(uiState.pwdVisibility, uiEvent)

        PasswordTextField(
            passwordState = uiState.pwd,
            passwordStateUpdate = { uiEvent(CreatePwdEvent.SetPwd(it)) },
            modifier = Modifier.padding(bottom = 16.dp),
            label = stringResource(id = R.string.new_password),
            showPassword = uiState.pwdVisibility,
            errorMsg = uiState.pwdErrMsgRes?.let { stringResource(id = it) }
        )

        PasswordTextField(
            passwordState = uiState.confirmPwd,
            passwordStateUpdate = { uiEvent(CreatePwdEvent.SetConfirmPwd(it)) },
            label = stringResource(id = R.string.confirm_password),
            showPassword = uiState.pwdVisibility,
            imeAction = ImeAction.Done,
            errorMsg = uiState.confirmPwdErrMsgRes?.let { stringResource(id = it) }
        )
    }
}

@Composable
private fun ColumnScope.PwdVisibilityIcon(
    pwdVisibility: Boolean,
    uiEvent: (CreatePwdEvent) -> Unit,
) {
    IconButton(
        onClick = { uiEvent(CreatePwdEvent.SetPwdVisibility(!pwdVisibility)) },
        modifier = Modifier
            .size(32.dp)
            .padding(4.dp)
            .align(Alignment.End),
    ) {
        Icon(
            painter = if (pwdVisibility) {
                painterResource(id = R.drawable.ic_visibility)
            } else {
                painterResource(id = R.drawable.ic_visibility_off)
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreatePwdField() {
    CreatePwdField(CreatePwdState()) {}
}