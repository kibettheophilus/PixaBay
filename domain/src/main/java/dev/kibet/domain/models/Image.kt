package dev.kibet.domain.models

data class Image(
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val likes: Int,
    val user: String,
    val userImageURL: String,
    val views: Int,
    val webformatURL: String,
)
