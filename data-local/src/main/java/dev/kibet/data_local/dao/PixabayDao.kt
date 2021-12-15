package dev.kibet.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kibet.data_local.entities.ImageEntity

@Dao
interface PixabayDao {
    // save all images
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImages(images: List<ImageEntity>)

    // get all images
    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<ImageEntity>

    // get an image by id
    @Query("SELECT * FROM images WHERE id=:id")
    suspend fun getImageDetails(id: Int): ImageEntity

    // get a list of images using their ids
    @Query("SELECT *FROM images WHERE id IN(:ids)")
    suspend fun getImagesByIds(ids: List<Int>): List<ImageEntity>
}
