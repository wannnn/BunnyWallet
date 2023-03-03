package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.theme.Purple80

val srpSolidBorder = Modifier.border(
    width = 1.dp,
    color = Purple80,
    shape = RoundedCornerShape(20.dp),
)

val srpDashedBorder = Modifier.drawBehind {
    drawRoundRect(
        color = Purple80,
        style = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
        ),
        cornerRadius = CornerRadius(20.dp.toPx()),
    )
}