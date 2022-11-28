
package xyz.database.data.animal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import xyz.database.data.animal.model.AnimalEntity
import xyz.database.data.animal.model.PhotoEntity
import xyz.database.data.animal.model.relations.AnimalWithPhotos

@Dao
interface AnimalDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimalEntity(animal: AnimalEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoEntity(photos: List<PhotoEntity>)

    @Transaction
    @Query("SELECT * FROM animal WHERE id=:id LIMIT 1")
    suspend fun get(id: Long): List<AnimalWithPhotos?>
}
