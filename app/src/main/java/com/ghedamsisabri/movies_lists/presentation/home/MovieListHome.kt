package com.ghedamsisabri.movies_lists

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.presentation.components.listMoviesView
import com.ghedamsisabri.movies_lists.presentation.home.MovieListEvent
import com.ghedamsisabri.movies_lists.presentation.home.MoviesListViewModel
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorBackGround
import com.skydoves.landscapist.glide.GlideImage
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

val list = mutableListOf<String>(
    "https://wallpaperaccess.com/full/1077148.jpg",
    "https://4kwallpapers.com/images/walls/thumbs_2t/7886.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdPM0LjvTSE5ve2klQZHH13x9yl8pjM9XELg&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6YP9EEkkHn1Xj29Zu-vq27XKifKkTUQmbIg&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwnkGluMSxooTvprlXeIfDAjjtV8XNeOHO4w&usqp=CAU",
    "https://wallpaperaccess.com/full/1768306.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkTFsNbo9ozDatRsd1Kg_KhytOBYI489kFiXQvzcyvBGQWfwEpELRF9a7vCS6k19FXsrc&usqp=CAU",
    "https://wallpaperaccess.com/full/2769762.jpg",
    "https://cutewallpaper.org/21/once-upon-a-time-wallpaper-hd/Free-download-Once-Upon-a-Time-Welcome-in-OZ-by-yoyoMonika-.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLFLMsVCpfjcqwe6aWvm92v4uOFSxzEQ60ZQ&usqp=CAU",
    "https://wallup.net/wp-content/uploads/2016/03/09/185321-Princess_Elsa-animated_movies-movies-Disney-Frozen_movie.jpg"

)

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovieListHome(
    navController: NavHostController,
    userName: String?,
    viewModel: MoviesListViewModel,
    onTriggerMovies: () -> Unit
) {
    val moviesTrending = viewModel.moviesTrending.value
    val moviesUpcoming = viewModel.moviesUpcoming.value
    val moviesBestNote = viewModel.moviesBestNote.value
    var currentPosition = mutableStateOf(0)
    val handler=Handler(Looper.getMainLooper())
    val listState = rememberLazyListState(
        if (true) Int.MAX_VALUE / 2 else 0
    )
    val coroutineScope = rememberCoroutineScope()
    val page = viewModel.page.value

    var name = if (userName.isNullOrEmpty()) "" else {
        if (userName.length >= 2) {
            userName.substring(0, 2).toUpperCase()
        } else {
            userName.toUpperCase()
        }
    }
    navController.addOnDestinationChangedListener(object :NavController.OnDestinationChangedListener{
        override fun onDestinationChanged(
            controller: NavController,
            destination: NavDestination,
            arguments: Bundle?
        ) {
            if(destination.route=="Screen.ListProfiles.route"){
                handler.removeCallbacksAndMessages(null)
            }
        }

    })
    Log.e("TAG", "MovieListHome: start" )
    fun setLooper() {
        handler.postDelayed({
            currentPosition.value += 1

            Log.e("TAG", "currentPosition:${currentPosition.value}")

            coroutineScope.launch {
                listState.animateScrollToItem(index = currentPosition.value)
                setLooper()

            }
        }, 2000)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = ColorBackGround
    ) {
        Column() {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)
            ) {
                Text(
                    text = "MOVIENIGHT",
                    fontSize = 19.sp,
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.weight(0.7f))
                Box(
                    modifier = Modifier
                        .width(
                            50.dp
                        )

                        .height(65.dp)
                        .aspectRatio(1f)
                        .background(Color(0xFFE0DCF5), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        name,
                        fontSize = 15.sp,
                        color = Color(0xFF040722),
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyRow(

                modifier = Modifier
                    .height(250.dp)
                    .padding(start = 16.dp, end = 8.dp, top = 8.dp),
                state = listState
            ) {
                if (!moviesTrending.isNullOrEmpty()){
                    currentPosition.value=1
                    setLooper()

                    items(moviesTrending.size) { index ->
                        Card(
                            modifier = if (currentPosition.value == index) Modifier
                                .padding(horizontal = 4.dp, vertical = 8.dp)
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 1000,
                                        easing = FastOutSlowInEasing
                                    )
                                )

                                .height(250.dp)
                                .width(350.dp) else
                                Modifier
                                    .padding(horizontal = 4.dp, vertical = 8.dp)
                                    .animateContentSize(
                                        animationSpec = tween(
                                            durationMillis = 1000,
                                            easing = FastOutSlowInEasing
                                        )
                                    )

                                    .height(100.dp)
                                    .width(125.dp),

                            elevation = 2.dp,
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(corner = CornerSize(4.dp))
                        ) {
                            GlideImage(
                                imageModel = "https://image.tmdb.org/t/p/w1280"+moviesTrending[index % moviesTrending.size].backdrop_path
                            )

                        }


                    }
                }

                else{
                    items(list.size) { index ->
                        Card(
                            modifier = if (currentPosition.value == index) Modifier
                                .padding(horizontal = 4.dp, vertical = 8.dp)
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 1000,
                                        easing = FastOutSlowInEasing
                                    )
                                )

                                .height(250.dp)
                                .width(350.dp) else
                                Modifier
                                    .padding(horizontal = 4.dp, vertical = 8.dp)
                                    .shimmer()
                                    .animateContentSize(
                                        animationSpec = tween(
                                            durationMillis = 1000,
                                            easing = FastOutSlowInEasing
                                        )
                                    )

                                    .height(100.dp)
                                    .width(125.dp),

                            elevation = 2.dp,
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(corner = CornerSize(4.dp))
                        ) {


                        }


                    }
                }

            }

            listMoviesView(
                "Les plus populaires",
                page,
                moviesTrending,
                viewModel::onChangeRecipeScrollPosition,
                onTriggerNextPage = { viewModel.onTriggerEvent(MovieListEvent.NextPageEvent) },)
            listMoviesView("Les Films a Venir", page,moviesUpcoming

                , viewModel::onChangeRecipeScrollPosition,
                onTriggerNextPage = { viewModel.onTriggerEvent(MovieListEvent.NextPageEvent) })
            listMoviesView("les mieux notés", page,moviesBestNote
                , viewModel::onChangeRecipeScrollPosition,
                onTriggerNextPage = { viewModel.onTriggerEvent(MovieListEvent.NextPageEvent) })

        }
    }



}

