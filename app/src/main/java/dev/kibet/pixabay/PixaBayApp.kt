package dev.kibet.pixabay

import android.app.Application
import dev.kibet.data_local.di.localDataModule
import dev.kibet.data_remote.remoteDataModule
import dev.kibet.domain.di.domainModule
import dev.kibet.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PixaBayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PixaBayApp)
            modules(
                listOf(presentationModule, remoteDataModule, domainModule, localDataModule)
            )
        }
    }
}
