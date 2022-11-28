
package xyz.database.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.database.data.animal.AnimalDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PetFinderDatabase {
        val builder = Room.databaseBuilder(
            context,
            PetFinderDatabase::class.java,
            "petfinder_database"
        )
            .fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    fun provideAnimalDao(
        database: PetFinderDatabase,
    ): AnimalDao {
        return database.animalDao()
    }
}
