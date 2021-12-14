package dev.kibet.data_remote.repository

import dev.kibet.data_remote.mappers.toDomain
import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.domain.models.Image
import dev.kibet.domain.repository.ImagesRepository
import dev.kibet.domain.utils.Constants.API_KEY

class ImageRepositoryImpl(private val api: ImagesApi) : ImagesRepository {
    override suspend fun getImages(keyWord: String): List<Image> {
        return api.getImages(API_KEY, keyWord).hits.map { it.toDomain() }
    }
}
