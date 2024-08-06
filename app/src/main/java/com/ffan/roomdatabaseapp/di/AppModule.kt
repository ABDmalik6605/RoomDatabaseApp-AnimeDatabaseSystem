package com.ffan.roomdatabaseapp.di

import MIGRATION_1_2
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ffan.roomdatabaseapp.data.local.dao.AnimeDao
import com.ffan.roomdatabaseapp.data.local.dao.UserDao
import com.ffan.roomdatabaseapp.data.local.database.AppDatabase
import com.ffan.roomdatabaseapp.data.repository.AnimeRepository
import com.ffan.roomdatabaseapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_1_2) // Add migration here
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideAnimeDao(appDatabase: AppDatabase): AnimeDao {
        return appDatabase.animeDao()
    }

    @Singleton
    @Provides
    fun provideAnimeRepository(animeDao: AnimeDao): AnimeRepository {
        return AnimeRepository(animeDao)
    }
}