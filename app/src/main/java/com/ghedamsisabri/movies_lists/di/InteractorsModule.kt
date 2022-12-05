package com.ghedamsisabri.movies_lists.di

import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.MovieTopRatedDao
import com.ghedamsisabri.movies_lists.cache.MovieTradingDao
import com.ghedamsisabri.movies_lists.cache.MovieUpcomingDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesTopRatedEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesTrendingEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesUpcomingEntityMapper
import com.ghedamsisabri.movies_lists.interactors.HomeMovies
import com.ghedamsisabri.movies_lists.interactors.HomeTrendingMovies
import com.ghedamsisabri.movies_lists.interactors.ProfIleUsers
import com.ghedamsisabri.movies_lists.network.MovieService
import com.ghedamsisabri.movies_lists.network.model.MovieDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {
    @ViewModelScoped
    @Provides
    fun provideProfIleUsers(
        movieDao: MovieDao
    ): ProfIleUsers {
        return ProfIleUsers(
            movieDao = movieDao
        )
    }

    @ViewModelScoped
    @Provides
    fun provideHomeMovies(
        movieDao: MovieDao,
        moviesEntityMapper: MoviesEntityMapper,
        provideMovieService: MovieService,
        movieDtoMapper: MovieDtoMapper,
        movieUpcomingDao: MovieUpcomingDao,
        moviesUpcomingEntityMapper: MoviesUpcomingEntityMapper,
        movieTopRatedDao: MovieTopRatedDao,
        moviesTopRatedEntityMapper: MoviesTopRatedEntityMapper

    ): HomeMovies {
        return HomeMovies(
            entityMapper = moviesEntityMapper,
            movieService = provideMovieService,
            movieDtoMapper = movieDtoMapper,
            movieDao = movieDao,
            movieUpcomingDao = movieUpcomingDao,
            moviesUpcomingEntityMapper = moviesUpcomingEntityMapper,
            movieTopRatedDao = movieTopRatedDao,
            moviesTopRatedEntityMapper = moviesTopRatedEntityMapper
        )
    }

    @ViewModelScoped
    @Provides
    fun provideHomeTrendingMovies(
        movieDao: MovieTradingDao,
        moviesEntityMapper: MoviesTrendingEntityMapper,
        provideMovieService: MovieService,
        movieDtoMapper: MovieDtoMapper,
    ): HomeTrendingMovies {
        return HomeTrendingMovies(
            movieTradingDao = movieDao,
            entityMapper = moviesEntityMapper,
            movieService = provideMovieService,
            movieDtoMapper = movieDtoMapper
        )
    }
}