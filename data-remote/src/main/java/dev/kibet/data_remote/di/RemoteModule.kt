package dev.kibet.data_remote

import dev.kibet.data_remote.remote.api.ImagesApi
import dev.kibet.data_remote.repository.ImageRepositoryImpl
import dev.kibet.domain.repository.ImagesRepository
import dev.kibet.domain.utils.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataModule = module {
    single <ImagesRepository> { ImageRepositoryImpl(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesApi::class.java)
    }
}
