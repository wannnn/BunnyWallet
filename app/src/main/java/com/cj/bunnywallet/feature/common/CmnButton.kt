package com.cj.bunnywallet.feature.common

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.ui.customview.BallPulseProgressIndicator

@Composable
fun CmnButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = !isLoading && enabled
    ) {
        if (isLoading) {
            BallPulseProgressIndicator()
        } else {
            Text(text)
        }
    }
}

@Composable
fun CmnOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = !isLoading && enabled,
        colors = colors,
    ) {
        if (isLoading) {
            BallPulseProgressIndicator(color = MaterialTheme.colorScheme.primary)
        } else {
            Text(text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCmnButton() {
    CmnButton("Button", {})
}

@Preview(showBackground = true)
@Composable
fun PreviewCmnButtonLoading() {
    CmnButton("Button", isLoading = true, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewCmnOutlineButton() {
    CmnOutlineButton("Button", {})
}

@Preview(showBackground = true)
@Composable
fun PreviewCmnOutlineButtonLoading() {
    CmnOutlineButton("Button", isLoading = true, onClick = {})
}