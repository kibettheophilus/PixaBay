package dev.kibet.data_local.di

import androidx.room.Room
import dev.kibet.data_local.db.PixDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PixDatabase::class.java, "pixabay.db")
            .fallbackToDestructiveMigration().build()
    }
    single { get<PixDatabase>().pixabayDao() }
}
