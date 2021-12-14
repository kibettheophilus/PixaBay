package dev.kibet.data_local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    val comments: Int,
    val downloads: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val likes: Int,
    val user: String,
    val userImageURL: String,
    val views: Int,
    val webformatURL: String,
)