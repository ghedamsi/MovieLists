package com.ghedamsisabri.movies_lists.di

import androidx.room.Room
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.database.AppDataBase
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDataBase {
        return Room
            .databaseBuilder(app, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDataBase): MovieDao {
        return db.profileDao()
    }

    @Singleton
    @Provides
    fun provideCacheMoviesEntityMapper(): MoviesEntityMapper {
        return MoviesEntityMapper()
    }

}