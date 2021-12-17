package dev.kibet.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kibet.data_local.dao.PixabayDao
import dev.kibet.data_local.dao.QueryDao
import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.data_local.entities.QueryEntity

@Database(entities = [ImageEntity::class, QueryEntity::class], version = 1, exportSchema = false)
abstract class PixDatabase : RoomDatabase() {
    abstract fun pixabayDao(): PixabayDao
    abstract fun queryDao(): QueryDao
}
