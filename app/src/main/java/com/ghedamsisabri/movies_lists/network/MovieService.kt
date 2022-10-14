package com.ghedamsisabri.movies_lists.network

import com.ghedamsisabri.movies_lists.network.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") query: String,
        @Query("page") page: Int,
    ): MoviesResponse
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") query: String,
        @Query("page") page: Int,
    ): MoviesResponse
    @GET("movie/top_rated")
    suspend fun getTopMovies(
        @Query("api_key") query: String,
        @Query("page") page: Int,
    ): MoviesResponse
}