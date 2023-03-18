package com.cj.bunnywallet.feature.createwallet.securewallet.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.ui.theme.Purple40

@Composable
fun SRPDialog(onDismiss: () -> Unit) {
    val annoRecoveryPhrase = buildAnnotatedString {
        append(stringResource(id = R.string.what_is).plus(" "))
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
        ) {
            append(stringResource(id = R.string.secret_recovery_phrase))
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = dismissLockProperties,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(size = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(all = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = annoRecoveryPhrase,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = stringResource(id = R.string.mnemonics_explanation_1),
                    style = MaterialTheme.typography.bodyLarge,
                )

                Text(
                    text = stringResource(id = R.string.mnemonics_explanation_2),
                    style = MaterialTheme.typography.bodyLarge,
                )

                Text(
                    text = stringResource(id = R.string.mnemonics_explanation_3),
                    style = MaterialTheme.typography.bodyLarge,
                )

                CmnButton(
                    text = stringResource(id = R.string.close),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onDismiss,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSRPDialog() {
    SRPDialog(onDismiss = {})
}