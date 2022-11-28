
package com.petfinder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petfinder.theme.animalCardBackgroundColor

@Composable
fun LoadingImage() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = animalCardBackgroundColor)
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}
