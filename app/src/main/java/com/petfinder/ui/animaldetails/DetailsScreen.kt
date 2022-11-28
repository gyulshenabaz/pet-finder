
package com.petfinder.ui.animaldetails

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.petfinder.api.animal.model.Animal
import com.petfinder.components.ExtendedFab
import com.petfinder.components.LoadingScreen
import com.petfinder.theme.AppTheme
import com.petfinder.util.exhaustive
import com.petfinder.util.previewAnimal

@Composable
fun DetailsScreen() {

    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    DisposableEffect(Unit) {
        val window = (context as? Activity)?.window
        val oldColor = window?.statusBarColor
        systemUiController.setStatusBarColor(Color.Transparent, true)
        onDispose {
            if (oldColor != null) {
                window.statusBarColor = oldColor
            }
        }
    }

    val viewModel: DetailsScreenViewModel = hiltViewModel()
    val viewState: DetailsScreenViewState by viewModel.viewStateFlow.collectAsState()

    DetailsScreen(viewState)
}

@Composable
private fun DetailsScreen(detailsScreenViewState: DetailsScreenViewState) {
    when (detailsScreenViewState) {
        DetailsScreenViewState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                LoadingScreen()
            }
        }
        is DetailsScreenViewState.Loaded -> {
            DetailsScreen(detailsScreenViewState.animal)
        }
    }.exhaustive
}

@Composable
private fun DetailsScreen(animal: Animal) {

    var selectedImageIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            PhotoSection(
                animal = animal,
                selectedImageIndex = selectedImageIndex,
                setSelectedImageIndex = {
                    selectedImageIndex = it
                },
                modifier = Modifier
                    .weight(0.45f)
                    .clip(
                        RoundedCornerShape(24.dp).copy(
                            topStart = ZeroCornerSize,
                            topEnd = ZeroCornerSize
                        )
                    )
            )
            DetailsSection(
                animal = animal,
                modifier = Modifier.weight(0.55f)
            )
        }
        
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            DetailsScreen(previewAnimal)
        }
    }
}
