package dev.kibet.data_remote.repository

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
        // get all saved keywords from local storage
        val keyWords = queryDao.getKeywords().map { it.keyWord }

        // check if the keyword entered by user is saved
        return if (keyWords.contains(keyWord)) {
            // get list image ids attached to the keyword
            val getImageIds = queryDao.getImageIds(keyWord).imageIds.toList()
            // get list of images using the list of ids
            val imageResponse = pixDao.getImagesByIds(getImageIds)
            imageResponse.map { it.toDomain() }
        } else {
            // get images from from api
            val imageResponse = api.getImages(API_KEY, keyWord)
            // get ids of the images from response and keyword entered by user
            val ids = imageResponse.hits.map { it.id }
            val query = QueryEntity(keyWord, ids)
            // save the ids with the query entered by user to local storage
            queryDao.saveQuery(query)
            // save images to local storage
            pixDao.saveImages(imageResponse.hits.map { it.toDomain().toEntity() })
            imageResponse.hits.map { it.toDomain() }
        }
    }

    override suspend fun getImageDetails(id: Int): Image {
        return pixDao.getImageDetails(id).toDomain()
    }
}
