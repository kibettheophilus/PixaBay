package dev.kibet.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kibet.data_local.entities.ImageEntity

@Dao
interface PixabayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveImages(images: List<ImageEntity>)

    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<ImageEntity>

    @Query("SELECT * FROM images WHERE id=:id")
    suspend fun getImageById(id: Int): ImageEntity

    @Query("SELECT * FROM images WHERE tags=:tags")
    suspend fun getImagesByKeyword(tags: String): List<ImageEntity>
}