package com.cj.bunnywallet.feature.createwallet.createpwd.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.ui.theme.Purple40

@Composable
fun CreatePwdField() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp)
                .align(Alignment.End),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_visibility),
                contentDescription = null,
                tint = Purple40,
            )
        }

        PasswordTextField(
            passwordState = "",
            passwordStateUpdate = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 16.dp),
            label = stringResource(id = R.string.new_password),
        )

        PasswordTextField(
            passwordState = "",
            passwordStateUpdate = { /*TODO*/ },
            label = stringResource(id = R.string.confirm_password),
        )

        Text(
            text = stringResource(id = R.string.password_condition_hint),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreatePwdField() {
    CreatePwdField()
}