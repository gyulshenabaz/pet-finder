
package com.petfinder.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier

@Composable
fun BoxScope.LoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier.align(
            BiasAlignment(verticalBias = -0.4f, horizontalBias = 0f)
        )
    )
}
