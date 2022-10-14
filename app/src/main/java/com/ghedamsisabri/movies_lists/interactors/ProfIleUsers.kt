package com.ghedamsisabri.movies_lists.interactors

import android.util.Log
import com.ghedamsisabri.movies_lists.cache.MovieDao
import com.ghedamsisabri.movies_lists.cache.model.ProfileEntity
import com.ghedamsisabri.movies_lists.domain.data.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfIleUsers(
    private val movieDao: MovieDao,
    ) {
    fun execute(): Flow<DataState<List<ProfileEntity>>> = flow {
        try {
            emit(DataState.loading())
            //just to show the progress bar in test removed in production
            delay(100)
            val profiles = movieDao.selectListProfiles()
            if (!profiles.isNullOrEmpty())
                emit(DataState.success(profiles))
            else
                emit(DataState.error("List Is Empty"))

        } catch (e: Exception) {
            emit(DataState.error<List<ProfileEntity>>(e.message ?: "Unknown error"))
        }

    }
    suspend fun insert(profileEntity:ProfileEntity): Long {
        val profiles:Long = movieDao.insertProfile(profileEntity)
        return profiles
        Log.e("TAG", "insert:$profiles " )


    }

}