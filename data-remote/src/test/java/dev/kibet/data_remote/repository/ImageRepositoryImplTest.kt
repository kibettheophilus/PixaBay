package dev.kibet.data_remote.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import dev.kibet.data_local.dao.PixabayDao
import dev.kibet.data_local.dao.QueryDao
import dev.kibet.data_local.db.PixDatabase
import dev.kibet.data_local.entities.ImageEntity
import dev.kibet.data_remote.mappers.toDomain
import dev.kibet.data_remote.mappers.toEntity
import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.data_remote.remote.models.HitDto
import dev.kibet.data_remote.remote.models.ImagesDto
import dev.kibet.domain.repository.ImagesRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ImageRepositoryImplTest {

    private lateinit var imagesRepository: ImagesRepository

    private lateinit var imagesApi: ImagesApi
    private lateinit var database: PixDatabase
    private lateinit var pixabayDao: PixabayDao
    private lateinit var queryDao: QueryDao

    private var hitDto =
        HitDto(1, 1, 1, 1, 1, 1, 1, "", 1, "", 1, "", 1, "", "", "", "", 1, 1, 1, "", 1)
    private var imagesDto = ImagesDto(listOf(hitDto), 2, 3)

    private lateinit var localData: List<ImageEntity>
    private lateinit var remoteData: ImagesDto

    @Before
    fun setUp() {
        imagesApi = mock()

        imagesApi.stub {
            onBlocking { imagesApi.getImages("", "") }.doReturn(imagesDto)
        }

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixDatabase::class.java
        ).allowMainThreadQueries().build()

        pixabayDao = database.pixabayDao()
        queryDao = database.queryDao()

        imagesRepository = ImageRepositoryImpl(imagesApi, pixabayDao, queryDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun fetchImagesFromApi() = runBlocking {
        val keywords = queryDao.getKeywords().map { it.keyWord }
        val isKeywordPresent = keywords.contains("dog")

        assertThat(isKeywordPresent).isEqualTo(false)

        if (isKeywordPresent) {
            queryDao.getImageIds("dog")
        } else {
            remoteData = imagesApi.getImages("", "")
            pixabayDao.saveImages(remoteData.hits.map { it.toDomain().toEntity() })
            localData = pixabayDao.getAllImages()
        }
        assertThat(remoteData).isEqualTo(imagesDto)
    }

    @Test
    fun fetchImagesFromLocal() = runBlocking {
        val keywords = queryDao.getKeywords().map { it.keyWord }
        val isKeywordPresent = keywords.contains("dog")

        assertThat(isKeywordPresent).isEqualTo(true)

        if (isKeywordPresent) {
            val ids = queryDao.getImageIds("dog").imageIds.toList()
            localData = pixabayDao.getImagesByIds(ids)
            assertThat(localData).isNotEmpty()
        } else {
            remoteData = imagesApi.getImages("", "")
            pixabayDao.saveImages(remoteData.hits.map { it.toDomain().toEntity() })
            localData = pixabayDao.getAllImages()
        }
        assertThat(localData).isEqualTo(imagesDto.hits.map { it.toDomain().toEntity() }.toList())
    }
}
