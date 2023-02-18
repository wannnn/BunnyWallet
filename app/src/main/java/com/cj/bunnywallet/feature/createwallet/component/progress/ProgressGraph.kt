package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressGraph() {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProgressBall("1", true)
        ProgressLine(true)
        ProgressBall("2", false)
        ProgressLine(false)
        ProgressBall("3", false)
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProgressGraph() {
    ProgressGraph()
}