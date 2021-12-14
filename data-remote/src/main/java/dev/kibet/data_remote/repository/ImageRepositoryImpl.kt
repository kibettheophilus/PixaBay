package dev.kibet.data_remote.repository

import dev.kibet.data_local.dao.PixabayDao
import dev.kibet.data_remote.mappers.toDomain
import dev.kibet.data_remote.mappers.toEntity
import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.domain.models.Image
import dev.kibet.domain.repository.ImagesRepository
import dev.kibet.domain.utils.Constants.API_KEY

class ImageRepositoryImpl(private val api: ImagesApi, private val dao: PixabayDao) :
    ImagesRepository {
    override suspend fun getImages(keyWord: String): List<Image> {
        val imageResponse = dao.getImagesByKeyword(keyWord)
        return if (imageResponse.isNotEmpty()) {
            imageResponse.map { it.toDomain() }
        } else {
            val imageResponse = api.getImages(API_KEY, keyWord)
            dao.saveImages(imageResponse.hits.map { it.toDomain().toEntity() })
            imageResponse.hits.map { it.toDomain() }
        }
    }
}
