package com.cj.bunnywallet.feature.createwallet.securewallet.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.CmnOutlineButton
import com.cj.bunnywallet.feature.createwallet.component.Declaration

@Composable
fun WarnSkipDialog(onDismiss: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { /** BackPress and ClickOutside are locked **/ },
        properties = dismissLockProperties,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(size = 10.dp),
        ) {
            Column(
                modifier = Modifier.padding(all = 28.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.Red,
                )

                Text(
                    text = stringResource(id = R.string.skip_account_security),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                )

                var checked by remember { mutableStateOf(false) }

                Declaration(
                    declaration = R.string.skip_warning_declaration,
                    checked = checked,
                    onCheckedChange = { checked = it },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CmnOutlineButton(
                        text = stringResource(id = R.string.secure_now),
                        modifier = Modifier.weight(1f),
                        onClick = { onDismiss(false) },
                    )

                    CmnButton(
                        text = stringResource(id = R.string.skip),
                        modifier = Modifier.weight(1f),
                        onClick = { onDismiss(true) },
                        enabled = checked,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWarnSkipDialog() {
    WarnSkipDialog(onDismiss = {})
}