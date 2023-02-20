package com.cj.bunnywallet.feature.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.ui.theme.Gray200
import com.cj.bunnywallet.ui.theme.Gray700
import com.cj.bunnywallet.ui.theme.Lavender100
import com.cj.bunnywallet.ui.theme.PurpleGrey80
import com.cj.bunnywallet.ui.theme.Red400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    valueState: String,
    onValueUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMsg: String? = null,
) {

    Column(modifier) {
        OutlinedTextField(
            value = valueState,
            onValueChange = { onValueUpdate.invoke(it.replace("\n", "")) },
            modifier = Modifier.fillMaxWidth(),
            label = if (label.isEmpty()) {
                null
            } else {
                { Label(label = label) }
            },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(size = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Gray700,
                containerColor = Lavender100,
                focusedBorderColor = PurpleGrey80,
                unfocusedBorderColor = Gray200,
                cursorColor = PurpleGrey80
            )
        )

        if (errorMsg != null) {
            Error(errorMsg)
        }
    }
}

@Composable
private fun Label(label: String) {
    Text(
        text = label,
        color = PurpleGrey80,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun Error(errorMsg: String) {
    Text(
        text = errorMsg,
        modifier = Modifier.padding(start = 8.dp),
        color = Red400,
        fontSize = 12.sp
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommonTextField() {
    CommonTextField(
        valueState = "Test String",
        onValueUpdate = {}
    )
}