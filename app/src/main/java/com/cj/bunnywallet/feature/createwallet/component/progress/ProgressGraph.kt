package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.theme.Purple40
import com.cj.bunnywallet.ui.theme.Purple80

@Composable
fun ProgressGraph() {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.85f)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProgressBall("1", false)
        ProgressLine(false)
        ProgressBall("2", false)
        ProgressLine(false)
        ProgressBall("3", false)
    }
}


private val outlined = Modifier
    .clip(CircleShape)
    .border(2.dp, Purple40, CircleShape)

private val filled = Modifier
    .background(Purple40, shape = CircleShape)

@Composable
private fun ProgressBall(step: String, hasDone: Boolean) {
    Text(
        text = step,
        modifier = Modifier
            .then((if (hasDone) filled else outlined))
            .size(24.dp)
            .wrapContentHeight(),
        color = if (hasDone) Color.White else Color.Black,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun RowScope.ProgressLine(hasDone: Boolean) {
    Divider(
        modifier = Modifier.weight(1f, true),
        thickness = 2.dp,
        color = if (hasDone) Purple40 else Purple80,
    )
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProgressGraph() {
    ProgressGraph()
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressBall() {
    ProgressBall("1", false)
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressLine() {
    Row {
        ProgressLine(true)
    }
}