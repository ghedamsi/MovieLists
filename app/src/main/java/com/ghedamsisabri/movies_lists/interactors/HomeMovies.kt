package com.ghedamsisabri.movies_lists.interactors

import android.util.Log
import com.ghedamsisabri.Const
import com.ghedamsisabri.Const.MOVIES_PAGINATION_PAGE_SIZE
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.MovieTopRatedDao
import com.ghedamsisabri.movies_lists.cache.MovieUpcomingDao
import com.ghedamsisabri.movies_lists.cache.model.MovieUpcomingEntity
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesTopRatedEntityMapper
import com.ghedamsisabri.movies_lists.cache.model.MoviesUpcomingEntityMapper
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
    private val movieUpcomingDao: MovieUpcomingDao,
    private val moviesUpcomingEntityMapper: MoviesUpcomingEntityMapper,
    private val movieTopRatedDao: MovieTopRatedDao,
    private val moviesTopRatedEntityMapper: MoviesTopRatedEntityMapper
    ) {

    fun execute(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute: ", )
                emit(DataState.loading())
                var movies = getListMoviesFromNetwork(page)

                // just to show loading, cache is fast
                delay(1000)

                // insert into cache
                movieDao.insertMovies(entityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }

            // query the cache
            val cacheResult = movieDao.getAllMovies(
                pageSize = MOVIES_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }

     fun homeUpcomingMovieGetById(id:String) : Flow<DataState<Movies>> = flow {
        try {
            var movieResult=movieUpcomingDao.getMoviesById(id)
            val list = moviesUpcomingEntityMapper.fromEntityList(movieResult)
            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<Movies>(e.message ?: "Unknown Error"))
             Log.e("TAG", "nextPage: Unknown Error")

        }
    }
    fun homeTopRatedMovieGetById(id:String) : Flow<DataState<Movies>> = flow {
        try {
            var movieResult=movieTopRatedDao.getMoviesById(id)
            val list = moviesUpcomingEntityMapper.fromEntityList(movieResult)
            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<Movies>(e.message ?: "Unknown Error"))
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



                // insert into cache
                movieUpcomingDao.insertMovies(moviesUpcomingEntityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }
            delay(4000)

            // query the cache
            val cacheResult = movieUpcomingDao.getAllMovies(
                pageSize = MOVIES_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = moviesUpcomingEntityMapper.fromEntityList(cacheResult)

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
                movieTopRatedDao.insertMovies(moviesTopRatedEntityMapper.toEntityList(movies))
            }catch (e:Exception){
                e.printStackTrace()

            }
            delay(4000)

            // query the cache
            val cacheResult = movieTopRatedDao.getAllMovies(
                pageSize = MOVIES_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = moviesTopRatedEntityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }
    private suspend fun getListMoviesFromNetwork(      page: Int,
    ): List<Movies> {
        return movieDtoMapper.toDomainList(movieService.getListMovies(Const.API_KEY, page = page).movies)

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