package com.cj.bunnywallet.feature.createwallet.securewallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R

@Composable
fun SecureWalletTitle(
    onSRPClick: () -> Unit,
    onWhyImportantClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.secure_wallet),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )
        RecoveryPhrase(onSRPClick)
        WhyIsImportant(onWhyImportantClick)
    }
}

@Composable
private fun ColumnScope.RecoveryPhrase(onClick: () -> Unit) {
    val recoveryPhrase = stringResource(id = R.string.secret_recovery_phrase)
    val annoRecoveryPhrase = buildAnnotatedString {
        append(stringResource(id = R.string.secure_your_wallets).plus(" "))
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = recoveryPhrase, annotation = recoveryPhrase)
            append(recoveryPhrase)
        }
    }

    ClickableText(
        text = annoRecoveryPhrase,
        modifier = Modifier.align(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.bodyMedium
            .merge(
                TextStyle(
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                ),
            ),
        onClick = { offset ->
            annoRecoveryPhrase.getStringAnnotations(offset, offset)
                .firstOrNull()
                ?.let { onClick() }
        },
    )
}

@Composable
private fun WhyIsImportant(onClick: () -> Unit) {
    val annoImportantInfo = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(stringResource(id = R.string.why_is_it_important))
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        ClickableText(
            text = annoImportantInfo,
            modifier = Modifier.padding(horizontal = 4.dp),
            style = MaterialTheme.typography.bodyLarge,
            onClick = { onClick() },
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewSecureWalletTitle() {
    SecureWalletTitle(
        onSRPClick = {},
        onWhyImportantClick = {},
    )
}