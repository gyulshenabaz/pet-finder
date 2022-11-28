
package com.petfinder.util

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

@ExperimentalMaterialApi
@Composable
@Stable
fun BottomSheetState.swipeProgressAsState(totalDragDistance: Float): State<Float> {
    val bottomSheetOffset by this.offset
    return remember(this, totalDragDistance) {
        derivedStateOf {
            when {
                bottomSheetOffset.isNaN() || totalDragDistance <= 0 -> 0f
                else -> 1f - ((bottomSheetOffset) / totalDragDistance).coerceIn(0f, 1f)
            }
        }
    }
}

fun DrawScope.fillDrawScope(
    color: Color,
) {
    this.drawRect(
        color = color,
        size = Size(
            width = this.size.width,
            height = this.size.height,
        )
    )
}
