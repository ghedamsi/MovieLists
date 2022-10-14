package com.ghedamsisabri.movies_lists.domain.model

import com.google.gson.annotations.SerializedName

class Movies(    var adult: Boolean,
                 var backdrop_path: String,
                 var id: String,
                 var title: String,
                 var original_language: String,
                 var original_title: String,
                 var overview: String,
                 var genre_ids: List<String> = emptyList(),
                 var popularity: String,
                 var release_date: String,
                 var video: Boolean,
                 var vote_average: Float,
                 var vote_count: Long,)