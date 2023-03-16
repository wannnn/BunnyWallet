package com.cj.bunnywallet.feature.createwallet.component.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.ui.theme.Gray300

@Composable
fun ProgressGraph(step: CreateWalletStep) {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.85f)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProgressBall(
            stepNum = CreateWalletStep.CREATE_PWD.stepNum,
            modifier = Modifier.ballColor(step, CreateWalletStep.CREATE_PWD),
            textColor = ballTextColor(step, CreateWalletStep.CREATE_PWD),
        )

        ProgressLine(color = lineColor(step, CreateWalletStep.CREATE_PWD))

        ProgressBall(
            stepNum = CreateWalletStep.SECURE_WALLET.stepNum,
            modifier = Modifier.ballColor(step, CreateWalletStep.SECURE_WALLET),
            textColor = ballTextColor(step, CreateWalletStep.SECURE_WALLET),
        )

        ProgressLine(color = lineColor(step, CreateWalletStep.SECURE_WALLET))

        ProgressBall(
            stepNum = CreateWalletStep.CONFIRM_SRP.stepNum,
            modifier = Modifier.ballColor(step, CreateWalletStep.CONFIRM_SRP),
            textColor = ballTextColor(step, CreateWalletStep.CONFIRM_SRP),
        )
    }
}

@Composable
private fun ProgressBall(stepNum: String, modifier: Modifier, textColor: Color) {
    Text(
        text = stepNum,
        modifier = Modifier
            .then(modifier)
            .size(24.dp)
            .wrapContentHeight(),
        color = textColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun RowScope.ProgressLine(color: Color) {
    Divider(
        modifier = Modifier.weight(1f, true),
        thickness = 2.dp,
        color = color,
    )
}

private val doneBackground = Modifier.composed {
    background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
}

private val inProgressOutline = Modifier.composed {
    this
        .clip(shape = CircleShape)
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.secondary,
            shape = CircleShape,
        )
}

private val preparationOutline = Modifier
    .clip(shape = CircleShape)
    .border(width = 2.dp, color = Gray300, shape = CircleShape)

private fun Modifier.ballColor(step: CreateWalletStep, compareState: CreateWalletStep) = then(
    when {
        step == compareState -> inProgressOutline
        step > compareState -> doneBackground
        else -> preparationOutline
    }
)

private fun ballTextColor(step: CreateWalletStep, compareState: CreateWalletStep) = when {
    step == compareState -> Color.Black
    step > compareState -> Color.White
    else -> Gray300
}

@Composable
private fun lineColor(step: CreateWalletStep, compareState: CreateWalletStep) = when {
    step > compareState -> MaterialTheme.colorScheme.secondary
    else -> Gray300
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun PreviewProgressGraph() {
    Column {
        ProgressGraph(step = CreateWalletStep.CREATE_PWD)
        ProgressGraph(step = CreateWalletStep.SECURE_WALLET)
        ProgressGraph(step = CreateWalletStep.CONFIRM_SRP)
        ProgressGraph(step = CreateWalletStep.DONE)
    }
}