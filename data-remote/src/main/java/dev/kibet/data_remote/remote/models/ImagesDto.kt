package dev.kibet.data_remote.remote.models

data class ImagesDto(
    val hits: List<HitDto>,
    val total: Int,
    val totalHits: Int
)