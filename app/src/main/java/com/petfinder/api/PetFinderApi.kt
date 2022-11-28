
package com.petfinder.api

import com.petfinder.api.animal.response.AllAnimalsResponse
import com.petfinder.api.animal.response.AnimalResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PetFinderApi {
    @GET("animals")
    suspend fun fetchAnimals(
        @Query("type") animalType: String,
        @Query("page") page: Int,
    ): AllAnimalsResponse

    @GET("animals/{id}")
    suspend fun fetchAnimal(
        @Path("id") id: Long
    ): AnimalResponse
}
