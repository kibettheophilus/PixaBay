package dev.kibet.data_local.dao

import androidx.room.*
import dev.kibet.data_local.converters.IdConverters
import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.data_local.entities.QueryEntity

@Dao
interface PixabayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImages(images: List<ImageEntity>)

    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<ImageEntity>

    @Query("SELECT * FROM images WHERE id=:id")
    suspend fun getImageDetails(id: Int): ImageEntity

    @Query("SELECT *FROM images WHERE id IN(:ids)")
    suspend fun getImagesByIds(ids: List<Int>): List<ImageEntity>

//    @Query("SELECT * FROM images WHERE `query` = :keyWord")
//    suspend fun getImagesByKeyword(keyWord: String): List<ImageEntity>
}
