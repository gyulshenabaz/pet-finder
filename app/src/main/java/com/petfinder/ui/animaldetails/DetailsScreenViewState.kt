
package com.petfinder.ui.animaldetails

import androidx.compose.runtime.Immutable
import com.petfinder.api.animal.model.Animal

@Immutable
sealed class DetailsScreenViewState {
    object Loading : DetailsScreenViewState()

    @Immutable
    data class Loaded(val animal: Animal) : DetailsScreenViewState()
}
