package dev.kibet.data_remote.repository

import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.domain.models.Images
import dev.kibet.domain.repository.ImagesRepository

class ImageRepositoryImpl(private val api: ImagesApi) : ImagesRepository {
    override suspend fun getImages(keyWord: String): Images {
        TODO("Not yet implemented")
    }
}
