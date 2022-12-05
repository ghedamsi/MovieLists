package com.ghedamsisabri.movies_lists.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies_trading")
data class MoviesTradingEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "original_language")
    var original_language: String,

    @ColumnInfo(name = "original_title")
    var original_title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "genre_ids")
    var genre_ids:  String = "",

    @ColumnInfo(name = "popularity")
    var popularity: String,

    @ColumnInfo(name = "release_date")
    var release_date: String,

    @ColumnInfo(name = "video")
    var video: Boolean,

    @ColumnInfo(name = "vote_average")
    var vote_average: Float,

    @ColumnInfo(name = "vote_count")
    var vote_count: Long,
)