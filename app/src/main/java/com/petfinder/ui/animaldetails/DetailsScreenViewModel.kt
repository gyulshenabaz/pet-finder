
package com.petfinder.ui.animaldetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.petfinder.data.PetFinderRepository
import com.petfinder.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val petFinderRepository: PetFinderRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val animalIdFlow: Flow<Long> = savedStateHandle.getLiveData<Long>("id").asFlow()

    val viewStateFlow: StateFlow<DetailsScreenViewState> = animalIdFlow
        .map { animalId: Long ->
            val animal = petFinderRepository.getAnimal(animalId)
            DetailsScreenViewState.Loaded(animal)
        }
        .stateIn(
            scope = viewModelScope + defaultDispatcher,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsScreenViewState.Loading
        )
}
