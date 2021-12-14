package dev.kibet.domain.di

import dev.kibet.domain.usecases.GetImagesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetImagesUseCase(get()) }
}
