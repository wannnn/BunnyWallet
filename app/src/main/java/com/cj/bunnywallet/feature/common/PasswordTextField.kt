package com.cj.bunnywallet.feature.common

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.ui.customview.VisibilityIconBtn

@Composable
fun PasswordTextField(
    passwordState: String,
    passwordStateUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    showPassword: Boolean = true,
    trailingIconClick: (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMsg: String? = null,
) {
    CommonTextField(
        valueState = passwordState,
        onValueUpdate = { passwordStateUpdate.invoke(it.trim()) },
        modifier = modifier,
        label = label,
        trailingIcon = trailingIconClick?.let {
            { VisibilityIconBtn(showPassword, trailingIconClick) }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardType = KeyboardType.Password,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        errorMsg = errorMsg,
    )
}

@Composable
fun PasswordTailIconTextField(
    passwordState: String,
    passwordStateUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMsg: String? = null,
) {
    var pwdVisibility by remember { mutableStateOf(false) }

    PasswordTextField(
        passwordState = passwordState,
        passwordStateUpdate = passwordStateUpdate,
        modifier = modifier,
        label = label,
        showPassword = pwdVisibility,
        trailingIconClick = { pwdVisibility = !pwdVisibility },
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        errorMsg = errorMsg,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordTextField() {
    val password = remember { mutableStateOf("") }
    PasswordTextField(
        passwordState = password.value,
        passwordStateUpdate = { password.value = it },
        label = "Confirm Password"
    )
}