
package xyz.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.database.data.animal.AnimalDao
import xyz.database.data.animal.model.AnimalEntity
import xyz.database.data.animal.model.PhotoEntity

@Database(
    entities = [
        AnimalEntity::class,
        PhotoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PetFinderDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}
