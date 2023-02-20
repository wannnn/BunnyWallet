package com.cj.bunnywallet.feature.common

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    passwordState: String,
    passwordStateUpdate: (String) -> Unit,
    showPassword: Boolean = true,
    label: String = "",
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMsg: String? = null,
) {
    CommonTextField(
        valueState = passwordState,
        onValueUpdate = { passwordStateUpdate.invoke(it) },
        modifier = modifier,
        label = label,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardType = KeyboardType.Password,
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