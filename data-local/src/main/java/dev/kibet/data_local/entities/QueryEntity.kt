package dev.kibet.data_local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.kibet.data_local.converters.IdConverters

@Entity(tableName = "querytable")
@TypeConverters(IdConverters::class)
data class QueryEntity(
    @PrimaryKey(autoGenerate = false)
    val keyWord: String,
    val imageIds: List<Int>
)