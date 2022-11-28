
package com.petfinder.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

private val ColorPalette = lightColors(
    primary = White,
    primaryVariant = PurpleDark,
    secondary = PurpleDark,
    secondaryVariant = PurpleDark,
    background = Grey
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    val colors = ColorPalette

    MaterialTheme(
        colors = colors,
        typography = FindMyPetTypography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun bottomSheetShape(): Shape {
    val shapes = MaterialTheme.shapes
    return remember {
        shapes.large.copy(bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize)
    }
}

@Composable
fun floatingActionButtonDefaultElevation(): FloatingActionButtonElevation =
    FloatingActionButtonDefaults.elevation(
        defaultElevation = 2.dp,
        pressedElevation = 6.dp,
    )

val animalCardBackgroundColor: Color
    @Composable get() = MaterialTheme.colors.secondary.copy(alpha = 0.07f)
