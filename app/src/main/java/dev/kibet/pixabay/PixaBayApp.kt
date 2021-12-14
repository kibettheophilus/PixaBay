package dev.kibet.pixabay

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PixaBayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PixaBayApp)
            modules(
                listOf()
            )
        }
    }
}
