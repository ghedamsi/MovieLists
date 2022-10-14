package com.ghedamsisabri.movies_lists.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    var id: String,
    @SerializedName("adult")
    var adult: Boolean,

    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("original_title")
    var original_title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("genre_ids")
    var genre_ids: List<String> = emptyList(),

    @SerializedName("popularity")
    var popularity: String,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Float,

    @SerializedName("vote_count")
    var vote_count: Long,
)