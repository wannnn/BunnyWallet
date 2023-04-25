package com.cj.bunnywallet.feature.managewallet.edit.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.CmnOutlineButton
import com.cj.bunnywallet.feature.common.CommonTextField
import com.cj.bunnywallet.feature.common.DialogContainer
import com.cj.bunnywallet.feature.managewallet.edit.type.EditType
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun EditDialog(
    name: String,
    editType: EditType,
    onDismiss: (String) -> Unit,
) {
    DialogContainer(onDismiss = { onDismiss("") }) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(id = R.string.edit_dialog_title, editType.name),
                fontSize = 20.sp,
            )

            var newName by remember { mutableStateOf(name) }

            CommonTextField(
                valueState = newName,
                onValueUpdate = { newName = it },
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CmnOutlineButton(
                    text = stringResource(id = R.string.close),
                    modifier = Modifier.weight(1f),
                    onClick = { onDismiss("") },
                )

                CmnButton(
                    text = stringResource(id = R.string.save),
                    modifier = Modifier.weight(1f),
                    onClick = { onDismiss(newName.trim()) },
                    enabled = newName.isNotBlank() && newName != name,
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewEditWalletScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EditDialog(
                name = "Account 1",
                editType = EditType.Account,
                onDismiss = {},
            )
        }
    }
}