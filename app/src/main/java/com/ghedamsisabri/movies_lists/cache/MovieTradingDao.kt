package com.ghedamsisabri.movies_lists.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghedamsisabri.Const
import com.ghedamsisabri.movies_lists.cache.model.MovieUpcomingEntity
import com.ghedamsisabri.movies_lists.cache.model.MoviesTradingEntity

@Dao
interface MovieTradingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MoviesTradingEntity>): LongArray
    @Query("""
        SELECT * FROM movies_trading 
        ORDER BY release_date DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """)
    suspend fun getAllMovies(pageSize: Int= Const.MOVIES_PAGINATION_PAGE_SIZE, page: Int): List<MoviesTradingEntity>

    @Query("""
        SELECT * FROM movies_trading 
        WHERE id= :id
    """)
    suspend fun getMoviesById(id:String): MoviesTradingEntity
}