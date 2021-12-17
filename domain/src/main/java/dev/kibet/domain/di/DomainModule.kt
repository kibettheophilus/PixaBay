package dev.kibet.domain.di

import dev.kibet.domain.usecases.GetImageDetails
import dev.kibet.domain.usecases.GetImages
import org.koin.dsl.module

val domainModule = module {
    single { GetImages(get()) }
    single { GetImageDetails(get()) }
}
