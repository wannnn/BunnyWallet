package com.cj.bunnywallet.ui.customview

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val DefaultAnimationDuration = 500
private const val DefaultAnimationDelay = 200
private const val DefaultStartDelay = 0
private const val DefaultBallCount = 3

private val DefaultMaxBallDiameter = 10.dp
private val DefaultMinBallDiameter = 4.dp
private val DefaultSpacing = 4.dp

@Composable
fun BallPulseProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    animationDuration: Int = DefaultAnimationDuration,
    animationDelay: Int = DefaultAnimationDelay,
    startDelay: Int = DefaultStartDelay,
    ballCount: Int = DefaultBallCount,
    maxBallDiameter: Dp = DefaultMaxBallDiameter,
    minBallDiameter: Dp = DefaultMinBallDiameter,
    spacing: Dp = DefaultSpacing
) {
    val transition = rememberInfiniteTransition()

    val duration = startDelay + animationDuration + animationDelay

    // Fractional diameter
    val diameters = arrayListOf<Float>().apply {
        for (i in 0 until ballCount) {
            val delay = startDelay + (animationDelay / (ballCount - 1)) * i
            val diameter by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = duration
                        0f at delay with FastOutSlowInEasing
                        1f at (animationDuration / 2) + delay with FastOutSlowInEasing
                        0f at animationDuration + delay
                        0f at duration
                    },
                )
            )
            add(diameter)
        }
    }

    val width = (maxBallDiameter + spacing) * ballCount - spacing

    ProgressIndicator(modifier, width, maxBallDiameter) {
        drawIndeterminateBallPulseIndicator(
            maxDiameter = maxBallDiameter.toPx(),
            diameter = diameters.map { lerp(minBallDiameter.toPx(), maxBallDiameter.toPx(), it) },
            spacing = spacing.toPx(),
            color = color
        )
    }
}

private fun DrawScope.drawIndeterminateBallPulseIndicator(
    maxDiameter: Float,
    diameter: List<Float>,
    spacing: Float,
    color: Color
) {
    for (i in diameter.indices) {
        val x = i * (maxDiameter + spacing) + maxDiameter / 2
        drawCircle(
            color = color,
            radius = diameter[i] / 2,
            center = Offset(x, size.height / 2)
        )
    }
}

@Composable
internal fun ProgressIndicator(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(width, height)
            .focusable(),
        onDraw = onDraw
    )
}

internal fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + ((stop - start) * fraction)
}