
package com.petfinder.api.animal.model

import kotlinx.serialization.Serializable
import xyz.database.data.animal.model.PhotoEntity

@Serializable
data class Photo(
    val full: String,
    val large: String,
    val medium: String,
    val small: String,
)

fun PhotoEntity.toPhotos(): Photo = Photo(
    full = full,
    large = large,
    medium = medium,
    small = small,
)

fun Photo.toEntity(
    animalId: Long
): PhotoEntity = PhotoEntity(
    animalId = animalId,
    full = full,
    large = large,
    medium = medium,
    small = small,
)
