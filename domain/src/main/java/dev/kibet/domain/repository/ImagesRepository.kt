package dev.kibet.domain.repository

import dev.kibet.domain.models.Image

interface ImagesRepository {
    suspend fun getImages(keyWord: String): List<Image>
}
