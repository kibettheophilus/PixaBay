package dev.kibet.data_remote.repository

import android.util.Log
import dev.kibet.data_local.dao.PixabayDao
import dev.kibet.data_local.dao.QueryDao
import dev.kibet.data_local.entities.QueryEntity
import dev.kibet.data_remote.mappers.toDomain
import dev.kibet.data_remote.mappers.toEntity
import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.domain.models.Image
import dev.kibet.domain.repository.ImagesRepository
import dev.kibet.domain.utils.Constants.API_KEY

class ImageRepositoryImpl(
    private val api: ImagesApi,
    private val pixDao: PixabayDao,
    private val queryDao: QueryDao
) : ImagesRepository {
    override suspend fun getImages(keyWord: String): List<Image> {
        val keyWords = queryDao.getKeywords().map { it.keyWord }
        Log.d("KeyWords", "$keyWords $keyWord")

        return if (keyWords.contains(keyWord)) {
            val getImageIds = queryDao.getImageIds(keyWord).imageIds.toList()
            // val list = listOf()
            val imageResponse = pixDao.getImagesByIds(getImageIds)
            Log.d("ImageIds", "$getImageIds $keyWord")
            imageResponse.map { it.toDomain() }
        } else {
            val imageResponse = api.getImages(API_KEY, keyWord)
            val ids = imageResponse.hits.map { it.id }
            val query = QueryEntity(keyWord, ids)
            pixDao.saveImages(imageResponse.hits.map { it.toDomain().toEntity() })
            queryDao.saveQuery(query)
            imageResponse.hits.map { it.toDomain() }
        }
    }

    override suspend fun getImageDetails(id: Int): Image {
        return pixDao.getImageDetails(id).toDomain()
    }
}
