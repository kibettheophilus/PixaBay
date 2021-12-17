package dev.kibet.presentation.di

import dev.kibet.presentation.viewmodels.ImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ImagesViewModel(get(), get()) }
}
