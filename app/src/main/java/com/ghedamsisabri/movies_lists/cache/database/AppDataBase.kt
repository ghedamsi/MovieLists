package com.ghedamsisabri.movies_lists.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.model.MoviesEntity
import com.ghedamsisabri.movies_lists.cache.model.ProfileEntity

@Database(entities = [
    ProfileEntity::class,
    MoviesEntity::class

],    version=7
)
public abstract class AppDataBase:RoomDatabase() {

    abstract fun profileDao(): MovieDao

    companion object{
        val DATABASE_NAME="movie_dp"
    }
}