package com.ghedamsisabri.movies_lists.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghedamsisabri.Const.RECIPE_PAGINATION_PAGE_SIZE
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntity
import com.ghedamsisabri.movies_lists.cache.model.ProfileEntity

@Dao
interface MovieDao {
    //insert will return the row number if it is successfully or -1 if not
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfile(profile:ProfileEntity):Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MoviesEntity>): LongArray
    @Query("SELECT * FROM profiles WHERE id = :id")
    suspend fun selectProfile(id:Int):ProfileEntity

    @Query("SELECT * FROM profiles ")
    suspend fun selectListProfiles():List<ProfileEntity>


    @Query("""
        SELECT * FROM movies 
        ORDER BY release_date DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """)
    suspend fun getAllMovies(pageSize: Int=RECIPE_PAGINATION_PAGE_SIZE, page: Int): List<MoviesEntity>
}