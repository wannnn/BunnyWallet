package com.cj.bunnywallet.feature.common

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.ui.theme.Gray600
import com.cj.bunnywallet.ui.theme.Purple40
import com.cj.bunnywallet.ui.theme.PurpleGrey80

@Composable
fun CmnButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40,
            disabledContainerColor = PurpleGrey80,
            disabledContentColor = Gray600,
        )
    ) {
        Text(text)
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewCmnButton() {
    CmnButton("Button", {})
}