package com.ghedamsisabri.movies_lists.presentation.movieDetail

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghedamsisabri.movies_lists.cache.model.MovieUpcomingEntity
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.interactors.HomeMovies
import com.ghedamsisabri.movies_lists.interactors.HomeTrendingMovies
import com.ghedamsisabri.movies_lists.presentation.home.MovieListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalCoroutinesApi
@HiltViewModel
class MoviesDetailViewModel
    @Inject
    constructor(
        private val homeMovies: HomeMovies,

        private val homeTrendingMovies: HomeTrendingMovies,
    )
    : ViewModel() {
    var id = mutableStateOf("-1")
    var action = mutableStateOf("")

    var mMovie: MutableState<Movies> =mutableStateOf(Movies(false,"","","","","","",emptyList(),"","",false,1f,2L))
    private val loading = mutableStateOf(false)
    init {

    }
    fun onTriggerEvent(event: MovieDetailListEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is MovieDetailListEvent.NewMovieDetailListEvent -> {
                        Log.e(ContentValues.TAG, "onTriggerEvent:$id " )
                        execucte()
                    }

                    is MovieDetailListEvent.RestoreStateEvent->{
                    }
                }
            }catch (e: Exception){
                Log.e(ContentValues.TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
            finally {
                Log.d(ContentValues.TAG, "launchJob: finally called.")
            }
        }
    }

    fun execucte(){
        Log.e("TAG", "execucte id:${id}")
        when(action.value){
            "POPULAIRES"->{
                homeTrendingMovies.homeTopMovieById(id.value).onEach {dataState ->
                    loading.value=dataState.loading
                    dataState.data?.let {movie->
                        mMovie.value=movie
                        Log.e("TAG", "execucte movie:${movie.id}")
                    }
                    dataState.error?.let { error->
                        Log.e("TAG", "error movies List:$error " )
                    }
                }.launchIn( viewModelScope)
            }
            "VENIR"->{
                homeMovies.homeUpcomingMovieGetById(id.value).onEach {dataState ->
                    loading.value=dataState.loading
                    dataState.data?.let {movie->
                        mMovie.value=movie
                        Log.e("TAG", "execucte movie:${movie.id}")
                    }
                    dataState.error?.let { error->
                        Log.e("TAG", "error movies List:$error " )
                    }
                }.launchIn( viewModelScope)
            }
            "NOTES"->{
                homeMovies.homeTopRatedMovieGetById(id.value).onEach {dataState ->
                    loading.value=dataState.loading
                    dataState.data?.let {movie->
                        mMovie.value=movie
                        Log.e("TAG", "execucte movie:${movie.id}")
                    }
                    dataState.error?.let { error->
                        Log.e("TAG", "error movies List:$error " )
                    }
                }.launchIn( viewModelScope)
            }
        }
    }
}