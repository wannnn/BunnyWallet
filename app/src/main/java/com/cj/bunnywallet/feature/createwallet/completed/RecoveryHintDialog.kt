package com.cj.bunnywallet.feature.createwallet.completed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.CmnOutlineButton

private const val MAX_LENGTH = 100

@Composable
fun WarnSkipDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(size = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 28.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.recovery_hint),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = stringResource(id = R.string.recovery_hint_msg_1),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Text(
                    text = stringResource(id = R.string.recovery_hint_msg_2),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )

                HintField()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CmnOutlineButton(
                        text = stringResource(id = R.string.close),
                        modifier = Modifier.weight(1f),
                        onClick = onDismiss,
                    )

                    CmnButton(
                        text = stringResource(id = R.string.save),
                        modifier = Modifier.weight(1f),
                        onClick = onDismiss,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HintField() {
    Column(horizontalAlignment = Alignment.End) {
        var a by remember { mutableStateOf("") }
        OutlinedTextField(
            value = a,
            onValueChange = {
                if (it.contains("\n") || it.length > MAX_LENGTH) return@OutlinedTextField
                a = it
            },
            modifier = Modifier.height(100.dp),
        )

        Text(
            text = "${a.length} / $MAX_LENGTH",
            modifier = Modifier.padding(end = 2.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWarnSkipDialog() {
    WarnSkipDialog {}
}