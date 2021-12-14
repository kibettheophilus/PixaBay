package dev.kibet.data_remote.remote.api

import dev.kibet.data_remote.remote.models.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String,
        @Query("q") keyWord: String
    ): ImagesDto
}
