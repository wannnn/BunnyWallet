package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.theme.Purple40
import com.cj.bunnywallet.ui.theme.Purple80

@Composable
fun RowScope.ProgressLine(hasDone: Boolean) {
    Divider(
        modifier = Modifier.weight(1f, true),
        thickness = 2.dp,
        color = if (hasDone) Purple40 else Purple80,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressLine() {
    Row {
        ProgressLine(true)
    }
}