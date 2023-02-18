package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.theme.Purple40

private val outlined = Modifier
    .clip(CircleShape)
    .border(2.dp, Purple40, CircleShape)

private val filled = Modifier
    .background(Purple40, shape = CircleShape)

@Composable
fun ProgressBall(step: String, hasDone: Boolean) {
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

@Preview(showBackground = true)
@Composable
fun PreviewProgressBall() {
    ProgressBall("1", false)
}