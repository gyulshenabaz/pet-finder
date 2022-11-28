package com.petfinder.api.animal.model

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.database.data.animal.model.*
import xyz.database.data.animal.model.relations.AnimalWithPhotos

@Serializable
data class Animal(
    val age: String,
    val attributes: Attributes,
    val breeds: Breeds,
    val contact: Contact,
    val description: String?,
    val gender: String,
    val id: Long,
    val name: String,
    @SerialName("organization_animal_id")
    val organizationAnimalId: String?,
    @SerialName("organization_id")
    val organizationId: String,
    val photos: List<Photo>,
    @SerialName("primary_photo_cropped")
    val primaryPhotoCropped: PrimaryPhotoCropped?,
    @SerialName("published_at")
    val publishedAt: String,
    val size: String,
    val species: String,
    val status: String,
    @SerialName("status_changed_at")
    val statusChangedAt: String,
    val type: String,
    val url: String,
) {
    val simpleOneWordCapitalizedName: String
        get() = name.toLowerCase(Locale("en"))
            .split(" ")
            .first()
            .run {
                replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(java.util.Locale.ENGLISH) else it.toString()
                }
            }
}

fun AnimalWithPhotos.toAnimal(): Animal {
    val photoList: List<Photo> = this.photos.map(PhotoEntity::toPhotos)
    return animalEntity.run {
        Animal(
            age = age,
            attributes = attributesEntity.run {
                Attributes(
                    declawed = declawed,
                    houseTrained = houseTrained,
                    shotsCurrent = shotsCurrent,
                    spayedNeutered = spayedNeutered,
                    specialNeeds = specialNeeds,
                )
            },
            breeds = breedsEntity.run {
                Breeds(
                    mixed,
                    primary,
                    secondary,
                    unknown,
                )
            },
            contact = contactEntity.run {
                Contact(
                    address = addressEntity.run {
                        Contact.Address(
                            address = address,
                            city = city,
                            country = country,
                            postcode = postcode,
                            state = state,
                        )
                    },
                    email = email,
                    phone = phone,
                )
            },
            description = description,
            gender = gender,
            id = id,
            name = name,
            organizationAnimalId = organizationAnimalId,
            organizationId = organizationId,
            photos = photoList,
            primaryPhotoCropped = primaryPhotoCroppedEntity?.run {
                PrimaryPhotoCropped(
                    full = full,
                    large = large,
                    medium = medium,
                    small = small,
                )
            },
            publishedAt = publishedAt,
            size = size,
            species = species,
            status = status,
            statusChangedAt = statusChangedAt,
            type = type,
            url = url,
        )
    }
}

fun Animal.getAnimalEntity(): AnimalEntity {
    return AnimalEntity(
        age = age,
        attributesEntity = attributes.run {
            AttributesEntity(
                declawed = declawed,
                houseTrained = houseTrained,
                shotsCurrent = shotsCurrent,
                spayedNeutered = spayedNeutered,
                specialNeeds = specialNeeds,
            )
        },
        breedsEntity = breeds.run {
            BreedsEntity(
                mixed,
                primary,
                secondary,
                unknown,
            )
        },
        contactEntity = contact.run {
            ContactEntity(
                addressEntity = address.run {
                    ContactEntity.AddressEntity(
                        address = address,
                        city = city,
                        country = country,
                        postcode = postcode,
                        state = state,
                    )
                },
                email = email,
                phone = phone,
            )
        },
        description = description,
        gender = gender,
        id = id,
        name = name,
        organizationAnimalId = organizationAnimalId,
        organizationId = organizationId,
        primaryPhotoCroppedEntity = primaryPhotoCropped?.run {
            PrimaryPhotoCroppedEntity(
                full = full,
                large = large,
                medium = medium,
                small = small,
            )
        },
        publishedAt = publishedAt,
        size = size,
        species = species,
        status = status,
        statusChangedAt = statusChangedAt,
        type = type,
        url = url
    )
}

fun Animal.getPhotoEntityList(animalId: Long): List<PhotoEntity> {
    return this.photos.map { photo ->
        photo.toEntity(animalId = animalId)
    }
}
