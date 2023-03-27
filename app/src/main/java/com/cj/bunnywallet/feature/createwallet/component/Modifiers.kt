package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.ui.theme.Gray300

val srpSolidBorder = Modifier.composed {
    border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.inversePrimary,
        shape = RoundedCornerShape(32.dp),
    )
}

val srpDashedBorder = Modifier.composed {
    val color = MaterialTheme.colorScheme.inversePrimary
    drawBehind {
        drawRoundRect(
            color = color,
            style = Stroke(
                width = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
            ),
            cornerRadius = CornerRadius(32.dp.toPx()),
        )
    }
}

val srpGrayDashedBorder = Modifier.drawBehind {
    drawRoundRect(
        color = Gray300,
        style = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
        ),
        cornerRadius = CornerRadius(32.dp.toPx()),
    )
}
