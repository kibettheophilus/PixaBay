package dev.kibet.data_remote.remote.api

import dev.kibet.data_remote.remote.models.ImagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("")
    suspend fun getImages(
        @Query("") apiKey: String,
        @Query("") keyWord: String
    ): ImagesDto
}
