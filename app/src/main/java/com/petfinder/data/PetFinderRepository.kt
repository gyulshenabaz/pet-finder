
package com.petfinder.data

import com.petfinder.api.PetFinderApi
import com.petfinder.api.animal.model.Animal
import com.petfinder.api.animal.model.getAnimalEntity
import com.petfinder.api.animal.model.getPhotoEntityList
import com.petfinder.api.animal.model.toAnimal
import com.petfinder.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import xyz.database.data.animal.AnimalDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetFinderRepository @Inject constructor(
    private val petFinderApi: PetFinderApi,
    private val animalDao: AnimalDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun getAnimals(
        animalType: AnimalType,
        page: Int,
    ): List<Animal> = withContext(ioDispatcher) {
        val animals: List<Animal> = petFinderApi.fetchAnimals(
            animalType.type,
            page
        ).animals
            // Filter not 'nice' names for better looking UI
            .filterNot { animal ->
                animal.name.contains(
                    //language=RegExp
                    Regex("[^a-zA-Z ]")
                )
            }
            // Filter only ones with multiple pictures to showcase feature in details screen
            .filter { animal ->
                animal.photos.size > 1
            }
            .sortedByDescending { animal -> animal.id }
        insertAnimalsToDatabase(animals)
        animals
    }

    suspend fun getAnimal(
        id: Long
    ): Animal = withContext(ioDispatcher) {
        val databaseAnimal: Animal? = animalDao.get(id).firstOrNull()?.toAnimal()
        if (databaseAnimal == null) {
            val backendAnimal: Animal = petFinderApi.fetchAnimal(id).animal
            insertAnimalsToDatabase(listOf(backendAnimal))
            backendAnimal
        } else {
            databaseAnimal
        }
    }

    private suspend fun insertAnimalsToDatabase(animals: List<Animal>) {
        coroutineScope {
            animals.map { animal: Animal ->
                val asyncWork = async {
                    val id = animalDao.insertAnimalEntity(animal.getAnimalEntity())
                    animalDao.insertPhotoEntity(animal.getPhotoEntityList(id))
                }
                asyncWork.start()
                asyncWork
            }.awaitAll()
        }
    }
}
