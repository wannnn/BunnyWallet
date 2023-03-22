package com.cj.bunnywallet.ui.customview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.PasswordTextField

@Composable
fun CreatePwdView(
    title: String = "",
    pwd: String,
    onPwd: (String) -> Unit,
    pwdErrMsg: String?,
    confirmPwd: String,
    onConfirmPwd: (String) -> Unit,
    confirmPwdErrMsg: String?
) {
    var pwdVisibility by remember { mutableStateOf(false) }

    Column {
        VisibilityControlView(
            title = title,
            isVisible = pwdVisibility,
            onVisibilityChange = {
                pwdVisibility = pwdVisibility.not()
            }
        )

        PasswordTextField(
            passwordState = pwd,
            passwordStateUpdate = { onPwd.invoke(it.trim()) },
            modifier = Modifier.padding(bottom = 8.dp),
            label = stringResource(id = R.string.new_password),
            showPassword = pwdVisibility,
            errorMsg = pwdErrMsg
        )

        PasswordTextField(
            passwordState = confirmPwd,
            passwordStateUpdate = { onConfirmPwd.invoke(it.trim()) },
            label = stringResource(id = R.string.confirm_password),
            showPassword = pwdVisibility,
            errorMsg = confirmPwdErrMsg
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSetUpPwd() {
    CreatePwdView(
        pwd = "",
        onPwd = {},
        pwdErrMsg = null,
        confirmPwd = "",
        onConfirmPwd = {},
        confirmPwdErrMsg = null
    )
}