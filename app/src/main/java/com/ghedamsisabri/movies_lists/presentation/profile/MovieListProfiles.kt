package com.ghedamsisabri.movies_lists

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.cache.model.ProfileEntity
import com.ghedamsisabri.movies_lists.presentation.components.StartChoosingAccountItem
import com.ghedamsisabri.movies_lists.presentation.profile.ProfilesListViewModel

val recipesMain: MutableState<MutableList<ProfileEntity>> =
    mutableStateOf(mutableListOf(ProfileEntity(-1, "+", "", "")))
var displayPopupLoading = mutableStateOf(false)

@Composable
fun MovieListProfiles(
    navController: NavHostController,
    viewModelProfiles: ProfilesListViewModel
) {
    //

    Log.e("TAG", "startChoosingAccountItem: ")
    fun setList() {
        recipesMain.value.clear()
        recipesMain.value.addAll(viewModelProfiles.recipes.value)
        recipesMain.value.add(ProfileEntity(-1, "+", "", ""))
    }
    viewModelProfiles.getProfilesList()
    setList()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Image(
            painterResource(R.drawable.backgroun),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        StartChoosingAccountItem("MOVIENIGHT", navController, viewModelProfiles)
    }
}








