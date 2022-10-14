package com.ghedamsisabri.movies_lists.presentation.profile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghedamsisabri.movies_lists.cache.model.ProfileEntity
import com.ghedamsisabri.movies_lists.interactors.ProfIleUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfilesListViewModel
@Inject
constructor(
    private val profIleUsers: ProfIleUsers
): ViewModel()
{
    val recipes: MutableState<MutableList<ProfileEntity>> = mutableStateOf(mutableListOf())
    val loading = mutableStateOf(false)
    val  userName= mutableStateOf("")

    val insert: MutableState<Long> = mutableStateOf(-1L)
     fun getProfilesList(

    ){
        profIleUsers.execute().onEach {dataState ->
            loading.value=dataState.loading
            dataState.data?.let {list->
                recipes.value=list.toMutableList()
            }
            dataState.error?.let { error->
                Log.e("TAG", "error Profiles List:$error " )
            }
        }.launchIn( viewModelScope)

    }
      fun insertProfile(userFirstName: String, userLastName: String, password: String) {
          viewModelScope.launch {
              delay(2000)
              userName.value=userFirstName
             val insertItem:Long=profIleUsers.insert(ProfileEntity(userLastName = userLastName, userName = userFirstName, password = password))
              insert.value=insertItem
              getProfilesList()
          }
    }
}