package com.ghedamsisabri.movies_lists.network

import com.ghedamsisabri.movies_lists.network.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getListMovies(
        @Query("api_key") query: String,
        @Query("page") page: Int,
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun getListPopular(
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