package com.ghedamsisabri.movies_lists.presentation.home

sealed class MovieListEvent {
    object NewMovieEvent : MovieListEvent()

    object NextPageEvent : MovieListEvent()
    // restore after process death
    object RestoreStateEvent: MovieListEvent()
}