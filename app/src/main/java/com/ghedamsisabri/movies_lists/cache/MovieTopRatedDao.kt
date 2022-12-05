package com.ghedamsisabri.movies_lists.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghedamsisabri.Const
import com.ghedamsisabri.movies_lists.cache.model.MovieTopRatedEntity
import com.ghedamsisabri.movies_lists.cache.model.MovieUpcomingEntity

@Dao
interface MovieTopRatedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieTopRatedEntity>): LongArray

    @Query(
        """
        SELECT * FROM movies_top_rated 
        ORDER BY release_date DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """
    )
    suspend fun getAllMovies(
        pageSize: Int = Const.MOVIES_PAGINATION_PAGE_SIZE,
        page: Int
    ): List<MovieTopRatedEntity>

    @Query("""
        SELECT * FROM movies_top_rated 
        WHERE id= :id
    """)
    suspend fun getMoviesById(id:String): MovieUpcomingEntity
}