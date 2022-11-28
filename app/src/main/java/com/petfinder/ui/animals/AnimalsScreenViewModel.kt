
package com.petfinder.ui.animals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petfinder.api.animal.model.Animal
import com.petfinder.data.AnimalType
import com.petfinder.data.PetFinderRepository
import com.petfinder.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class AnimalsScreenViewModel @Inject constructor(
    private val petFinderRepository: PetFinderRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _animalType: MutableStateFlow<AnimalType> = MutableStateFlow(AnimalType.Cat)
    val animalType: StateFlow<AnimalType>
        get() = _animalType.asStateFlow()

    val animalListStateFlow: StateFlow<List<Animal>> = animalType
        .map { animalType: AnimalType ->
            coroutineScope {
                val getAnimalAsyncCalls: List<Deferred<List<Animal>>> = List(4) { index ->
                    async {
                        petFinderRepository.getAnimals(
                            animalType = animalType,
                            page = (index + 1)
                        )
                    }
                }
                val animalList: List<Animal> = getAnimalAsyncCalls
                    .awaitAll()
                    .flatten()
                    .distinct() // Remove potential duplicates because they break the lazyList by duplicating keys
                animalList
            }
        }
        .stateIn(
            scope = viewModelScope + defaultDispatcher, // Not sure about setting a dispatcher here
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = listOf()
        )

    fun setAnimalType(animalType: AnimalType) {
        _animalType.value = animalType
    }
}
