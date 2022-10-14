package com.ghedamsisabri.movies_lists.presentation.navigation

sealed class Screen(
    val route: String,
){
    object ListProfiles: Screen("listProfiles")

    object ListHome: Screen("listHome")
}