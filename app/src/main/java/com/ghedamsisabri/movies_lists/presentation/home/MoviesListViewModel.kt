package com.ghedamsisabri.movies_lists.presentation.home

import android.content.ContentValues.TAG
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.interactors.HomeMovies
import com.ghedamsisabri.movies_lists.interactors.HomeTrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
const val STATE_KEY_LIST_POSITION = "movie.state.query.list_position"
const val STATE_KEY_PAGE = "movie.state.page.key"

@ExperimentalCoroutinesApi
@HiltViewModel
class MoviesListViewModel
@Inject
constructor(
    private val homeMovies: HomeMovies,
    private val homeTrendingMovies: HomeTrendingMovies,

    private val savedStateHandle: SavedStateHandle

    ): ViewModel()
{
     val moviesTrending: MutableState<List<Movies>> = mutableStateOf(ArrayList())
     val moviesList: MutableState<List<Movies>> = mutableStateOf(ArrayList())
     val moviesUpcoming: MutableState<List<Movies>> = mutableStateOf(ArrayList())
    val moviesTopReated: MutableState<List<Movies>> = mutableStateOf(ArrayList())

    private val loading = mutableStateOf(false)
    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableStateOf(1)

    private var movieListScrollPosition = 0

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }

        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }

        if(movieListScrollPosition != 0){
            onTriggerEvent(MovieListEvent.RestoreStateEvent)
        }
        else{
            onTriggerEvent(MovieListEvent.NewMovieEvent)
        }

    }

    fun getTrending(
    ){
        homeTrendingMovies.executeTopMovies(page.value,true).onEach {dataState ->
            loading.value=dataState.loading
            dataState.data?.let {list->
                moviesTrending.value=list.toMutableList()
            }
            dataState.error?.let { error->
                Log.e("TAG", "error movies List:$error " )
            }
        }.launchIn( viewModelScope)

    }
    fun execute(
    ){
        homeMovies.execute(page.value,true).onEach {dataState ->
            loading.value=dataState.loading
            dataState.data?.let {list->
                moviesList.value=list.toMutableList()
            }
            dataState.error?.let { error->
                Log.e("TAG", "error movies List:$error " )
            }
        }.launchIn( viewModelScope)

    }
    fun getUpcoming(
    ){
        homeMovies.executeUpcomingMovies(page.value,true).onEach {dataState ->
            loading.value=dataState.loading
            dataState.data?.let {list->
                moviesUpcoming.value=list.toMutableList()
            }
            dataState.error?.let { error->
                Log.e("TAG", "error movies List:$error " )
            }
        }.launchIn( viewModelScope)

    }

    fun getTopMovie(
    ){
        homeMovies.executeTopMovies(page.value,true).onEach {dataState ->
            loading.value=dataState.loading
            dataState.data?.let {list->
                moviesTopReated.value=list.toMutableList()
            }
            dataState.error?.let { error->
                Log.e("TAG", "error movies List:$error " )
            }
        }.launchIn( viewModelScope)

    }
    fun onTriggerEvent(event: MovieListEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is MovieListEvent.NewMovieEvent -> {
                        Log.e(TAG, "onTriggerEvent: " )
                       execute()
                        getTrending()

                        getTopMovie()
                       getUpcoming()
                    }
                    is MovieListEvent.NextPageEvent -> {
                        nextPage()
                    }
                    is MovieListEvent.RestoreStateEvent->{
                        TODO("add logic to restore after death process")
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
            finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }
    private fun nextPage() {
        if ((movieListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")

            if (page.value > 1) {
                homeMovies.execute( page = page.value,true).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let { list ->
                        appendMovies(list)
                    }

                    dataState.error?.let { error ->
                        Log.e(TAG, "nextPage: ${error}")
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
    private fun incrementPage(){
        setPage(page.value + 1)
    }

    private fun setPage(page: Int){
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
        //add state to handle page
    }
    fun onChangeRecipeScrollPosition(position: Int){
        setListScrollPosition(position = position)
    }

    private fun setListScrollPosition(position: Int){
        movieListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }
    private fun appendMovies(recipes: List<Movies>){
        val current = ArrayList(this.moviesTrending.value)
        current.addAll(recipes)
        this.moviesTrending.value = current
    }

}