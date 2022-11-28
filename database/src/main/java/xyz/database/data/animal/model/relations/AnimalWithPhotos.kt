
package xyz.database.data.animal.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import xyz.database.data.animal.model.AnimalEntity
import xyz.database.data.animal.model.PhotoEntity

data class AnimalWithPhotos(
    @Embedded val animalEntity: AnimalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "animal_id",
    )
    val photos: List<PhotoEntity>
)
