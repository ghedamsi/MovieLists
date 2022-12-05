package com.ghedamsisabri.movies_lists.presentation.movieDetail

sealed class MovieDetailListEvent {
    object NewMovieDetailListEvent : MovieDetailListEvent()
    // restore after process death
    object RestoreStateEvent: MovieDetailListEvent()
}