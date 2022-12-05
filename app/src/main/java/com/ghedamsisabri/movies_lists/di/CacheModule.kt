package com.ghedamsisabri.movies_lists.di

import androidx.room.Room
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.MovieTopRatedDao
import com.ghedamsisabri.movies_lists.cache.MovieTradingDao
import com.ghedamsisabri.movies_lists.cache.database.AppDataBase
import com.ghedamsisabri.movies_lists.cache.MovieUpcomingDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesTopRatedEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesTrendingEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesUpcomingEntityMapper
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
    fun provideMoviesTradingDao(db: AppDataBase): MovieTradingDao {

        return db.moviesTradingDao()
    }
    @Singleton
    @Provides
    fun provideMovieUpcomingDao(db: AppDataBase): MovieUpcomingDao {

        return db.movieUpcomingDao()
    }

    @Singleton
    @Provides
    fun provideMovieTopRatedDao(db: AppDataBase):MovieTopRatedDao {

        return db.movieTopRatedDao()
    }
    @Singleton
    @Provides
    fun provideCacheMoviesEntityMapper(): MoviesEntityMapper {
        return MoviesEntityMapper()
    }
    @Singleton
    @Provides
    fun provideCacheMoviesTrendingEntityMapper(): MoviesTrendingEntityMapper {
        return MoviesTrendingEntityMapper()
    }
    @Singleton
    @Provides
    fun provideCacheMoviesUpcomingEntityMapper(): MoviesUpcomingEntityMapper {
        return MoviesUpcomingEntityMapper()
    }

    @Singleton
    @Provides
    fun provideCacheMoviesTopRatedEntityMapper(): MoviesTopRatedEntityMapper {
        return MoviesTopRatedEntityMapper()
    }
}