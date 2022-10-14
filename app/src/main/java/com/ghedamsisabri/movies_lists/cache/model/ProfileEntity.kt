package com.ghedamsisabri.movies_lists.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    @ColumnInfo(name = "id")
    val id: Int= 0,

    @ColumnInfo(name = "userFirstName")
    val userName: String,

    @ColumnInfo(name = "userLastName")
    val userLastName: String,

    @ColumnInfo(name = "password")
    val password: String
)