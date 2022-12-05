package com.ghedamsisabri.movies_lists.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghedamsisabri.Const.DATA_BASE_VERSION
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.MovieTopRatedDao
import com.ghedamsisabri.movies_lists.cache.MovieTradingDao
import com.ghedamsisabri.movies_lists.cache.MovieUpcomingDao
import com.ghedamsisabri.movies_lists.cache.model.*

@Database(entities = [
    ProfileEntity::class,
    MoviesEntity::class,
    MoviesTradingEntity::class,
    MovieUpcomingEntity::class,
    MovieTopRatedEntity::class



],    version=DATA_BASE_VERSION
)
public abstract class AppDataBase:RoomDatabase() {

    abstract fun profileDao(): MovieDao
    abstract fun moviesTradingDao(): MovieTradingDao
    abstract fun movieUpcomingDao(): MovieUpcomingDao
    abstract fun movieTopRatedDao(): MovieTopRatedDao

    companion object{
        val DATABASE_NAME="movie_dp"
    }
}