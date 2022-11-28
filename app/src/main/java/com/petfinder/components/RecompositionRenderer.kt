
package com.petfinder.components

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private operator fun Color.inc(): Color {
    fun Float.increaseColorByLittle(): Float =
        (this + 0.02f).coerceAtMost(1f)

    return when {
        red != 1f -> this.copy(red = red.increaseColorByLittle())
        green != 1f -> this.copy(green = green.increaseColorByLittle())
        blue != 1f -> this.copy(blue = blue.increaseColorByLittle())
        else -> Color(red = 0f, green = 0f, blue = 0f)
    }
}

fun Modifier.indicateRecompositions(width: Dp = 20.dp): Modifier = composed {
    var color by remember { mutableStateOf(Color(red = 255, green = 0, blue = 0, alpha = 128)) }

    SideEffect {
        color++
    }

    this.then(
        Modifier
            .drawWithContent {
                drawContent()
                val topLeftOffset = Offset(width.toPx() / 2, width.toPx() / 2)
                drawRect(
                    color = color,
                    style = Stroke(width = width.toPx()),
                    topLeft = topLeftOffset,
                    size = Size(
                        width = size.width - (topLeftOffset.x * 2),
                        height = size.height - (topLeftOffset.y * 2),
                    )
                )
            }
    )
}
