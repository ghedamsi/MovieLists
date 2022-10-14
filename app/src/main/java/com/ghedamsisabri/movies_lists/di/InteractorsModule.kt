package com.ghedamsisabri.movies_lists.di

import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.interactors.HomeMovies
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
object   InteractorsModule {
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
    ): HomeMovies {
        return HomeMovies(
            entityMapper=moviesEntityMapper,
            movieService=provideMovieService,
            movieDtoMapper=movieDtoMapper,

            movieDao = movieDao
        )
    }
}