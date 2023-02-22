package com.cj.bunnywallet.feature.createwallet.createpwd.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.NoRippleInteractionSource

@Composable
fun CreatePwdDeclaration() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Checkbox(
            checked = false,
            modifier = Modifier
                .size(12.dp)
                .padding(start = 8.dp, top = 12.dp),
            onCheckedChange = {
                /* TODO */
            },
            interactionSource = NoRippleInteractionSource()
        )

        Text(
            text = stringResource(id = R.string.create_password_declaration),
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCreatePwdDeclaration() {
    CreatePwdDeclaration()
}