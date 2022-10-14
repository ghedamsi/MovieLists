package com.ghedamsisabri.movies_lists.interactors

import android.content.ContentValues
import android.util.Log
import com.ghedamsisabri.Const
import com.ghedamsisabri.Const.RECIPE_PAGINATION_PAGE_SIZE
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.domain.data.DataState
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.network.MovieService
import com.ghedamsisabri.movies_lists.network.model.MovieDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeMovies(
    private val movieDao: MovieDao,
    private val entityMapper: MoviesEntityMapper,
    private val movieService: MovieService,
    private val movieDtoMapper: MovieDtoMapper,

    ) {

    fun execute(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute: ", )
                emit(DataState.loading())
                var movies = getTrendingFromNetwork(page)

                // just to show loading, cache is fast
                delay(1000)

                // insert into cache
                movieDao.insertMovies(entityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }

            // query the cache
            val cacheResult = movieDao.getAllMovies(
                pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }
    fun executeUpcomingMovies(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute: ", )
                emit(DataState.loading())
                var movies = getUpcomingMoviesFromNetwork(page)

                // just to show loading, cache is fast
                delay(4000)

                // insert into cache
                movieDao.insertMovies(entityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }

            // query the cache
            val cacheResult = movieDao.getAllMovies(
                pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }

    fun executeBestNote(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute: ", )
                emit(DataState.loading())
                var movies = getUpcomingMoviesFromNetwork(page)

                // just to show loading, cache is fast
                delay(4000)

                // insert into cache
                movieDao.insertMovies(entityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }

            // query the cache
            val cacheResult = movieDao.getAllMovies(
                pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }

    fun executeTopMovies(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute: ", )
                emit(DataState.loading())
                var movies = getTopMoviesFromNetwork(page)



                // insert into cache
                movieDao.insertMovies(entityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }
            delay(4000)

            // query the cache
            val cacheResult = movieDao.getAllMovies(
                pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }
    private suspend fun getTrendingFromNetwork(      page: Int,
    ): List<Movies> {
        return movieDtoMapper.toDomainList(movieService.getTrendingMovies(Const.API_KEY, page = page).movies)

    }

    private suspend fun getUpcomingMoviesFromNetwork(      page: Int,
    ): List<Movies> {
        return movieDtoMapper.toDomainList(movieService.getUpcomingMovies(Const.API_KEY, page = page).movies)

    }

    private suspend fun getTopMoviesFromNetwork(      page: Int,
    ): List<Movies> {
        return movieDtoMapper.toDomainList(movieService.getTopMovies(Const.API_KEY, page = page).movies)

    }
}