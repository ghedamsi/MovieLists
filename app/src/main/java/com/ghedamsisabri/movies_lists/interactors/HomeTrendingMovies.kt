package com.ghedamsisabri.movies_lists.interactors

import android.util.Log
import com.ghedamsisabri.Const
import com.ghedamsisabri.movies_lists.cache.MovieTradingDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesTrendingEntityMapper
import com.ghedamsisabri.movies_lists.domain.data.DataState
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.network.MovieService
import com.ghedamsisabri.movies_lists.network.model.MovieDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeTrendingMovies(
    private val movieTradingDao: MovieTradingDao,
    private val entityMapper: MoviesTrendingEntityMapper,
    private val movieService: MovieService,
    private val movieDtoMapper: MovieDtoMapper,

    ) {

    fun executeTopMovies(
        page: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<List<Movies>>> = flow {
        try {
            try {
                Log.e("TAG", "execute Trending: ")
                emit(DataState.loading())
                var movies = getListPopularFromNetwork(page)

                // just to show loading, cache is fast
                delay(1000)

                // insert into cache
                movieTradingDao.insertMovies(entityMapper.toEntityList(movies))
            } catch (e: Exception) {
                e.printStackTrace()

            }

            // query the cache
            val cacheResult = movieTradingDao.getAllMovies(
                pageSize = Const.MOVIES_PAGINATION_PAGE_SIZE,
                page = page
            )
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        } catch (e: Exception) {
            emit(DataState.error<List<Movies>>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }


    }
    fun homeTopMovieById(id:String) : Flow<DataState<Movies>> = flow {
        try {
            var movieResult=movieTradingDao.getMoviesById(id)
            val list = entityMapper.fromEntityList(movieResult)
            emit(DataState.success(list))
        }catch (e: Exception) {
            emit(DataState.error<Movies>(e.message ?: "Unknown Error"))
            Log.e("TAG", "nextPage: Unknown Error")

        }
    }

    private suspend fun getListPopularFromNetwork(
        page: Int,
    ): List<Movies> {
        return movieDtoMapper.toDomainList(
            movieService.getListPopular(
                Const.API_KEY,
                page = page
            ).movies
        )

    }
}