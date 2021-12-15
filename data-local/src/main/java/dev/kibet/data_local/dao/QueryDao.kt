package dev.kibet.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kibet.data_local.entities.QueryEntity

@Dao
interface QueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuery(query: QueryEntity)

    @Query("SELECT * FROM querytable WHERE keyWord=:query")
    suspend fun getImageIds(query: String): QueryEntity

    @Query("SELECT * FROM querytable")
    suspend fun getKeywords(): List<QueryEntity>
}
