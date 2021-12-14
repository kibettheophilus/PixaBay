package dev.kibet.data_remote

import dev.kibet.data_remote.repository.ImageRepositoryImpl
import dev.kibet.domain.repository.ImagesRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single < ImagesRepository> { ImageRepositoryImpl(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
