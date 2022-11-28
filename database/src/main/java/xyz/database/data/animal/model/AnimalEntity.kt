
package xyz.database.data.animal.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
data class AnimalEntity(
    val age: String,
    @Embedded val attributesEntity: AttributesEntity,
    @Embedded val breedsEntity: BreedsEntity,
    @Embedded val contactEntity: ContactEntity,
    val description: String?,
    val gender: String,
    @PrimaryKey val id: Long,
    val name: String,
    val organizationAnimalId: String?,
    val organizationId: String,
    @Embedded val primaryPhotoCroppedEntity: PrimaryPhotoCroppedEntity?,
    val publishedAt: String,
    val size: String,
    val species: String,
    val status: String,
    val statusChangedAt: String,
    val type: String,
    val url: String,
)
