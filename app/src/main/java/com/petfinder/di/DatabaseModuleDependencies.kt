
package com.petfinder.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.database.data.PetFinderDatabase
import xyz.database.data.animal.AnimalDao

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DatabaseModuleDependencies {

    fun petFinderDatabase(): PetFinderDatabase

    fun animalDao(): AnimalDao
}
