package com.ghedamsisabri.movies_lists.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument

import com.ghedamsisabri.movies_lists.MovieListHome
import com.ghedamsisabri.movies_lists.MovieListProfiles
import com.ghedamsisabri.movies_lists.presentation.home.MovieListEvent
import com.ghedamsisabri.movies_lists.presentation.home.MoviesListViewModel
import com.ghedamsisabri.movies_lists.presentation.motionAnimation.materialSharedAxisXIn
import com.ghedamsisabri.movies_lists.presentation.motionAnimation.materialSharedAxisXOut
import com.ghedamsisabri.movies_lists.presentation.motionAnimation.rememberSlideDistance
import com.ghedamsisabri.movies_lists.presentation.movieDetail.MovieDetailPage
import com.ghedamsisabri.movies_lists.presentation.movieDetail.MoviesDetailViewModel
import com.ghedamsisabri.movies_lists.presentation.navigation.Screen
import com.ghedamsisabri.movies_lists.presentation.profile.ProfilesListViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("SuspiciousIndentation")
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoxWithConstraints {
                val navController = rememberAnimatedNavController()
                val slideDistance = rememberSlideDistance()

                val width = constraints.maxWidth / 2
                val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
                       AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.ListProfiles.route

                ) {

                    composable(
                        route = Screen.ListProfiles.route,
                        enterTransition = {
                            materialSharedAxisXIn(forward = true, slideDistance = slideDistance)
                        },
                        exitTransition = {
                            materialSharedAxisXOut(forward = true, slideDistance = slideDistance)
                        },
                        popEnterTransition = {
                            materialSharedAxisXIn(forward = false, slideDistance = slideDistance)
                        },
                        popExitTransition = {
                            materialSharedAxisXOut(forward = false, slideDistance = slideDistance)
                        },


                        ) { navBackStackEntry ->
                        val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
                        val viewModel: ProfilesListViewModel =viewModel(key="ProfilesListViewModel", factory = factory)
                        MovieListProfiles(navController,viewModel)
                    }
                    composable(
                        route = Screen.ListHome.route+"/{userName}",
                        arguments = listOf(navArgument("userName") { type = NavType.StringType }),
                        enterTransition = {
                            materialSharedAxisXIn(forward = true, slideDistance = slideDistance)
                        },
                        exitTransition = {
                            materialSharedAxisXOut(forward = true, slideDistance = slideDistance)
                        },
                        popEnterTransition = {
                            materialSharedAxisXIn(forward = false, slideDistance = slideDistance)
                        },
                        popExitTransition = {
                            materialSharedAxisXOut(forward = false, slideDistance = slideDistance)
                        },

                        ) { navBackStackEntry ->
                        val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)

                        val viewModel: MoviesListViewModel =viewModel(key="ProfilesListViewModel", factory = factory)

                        MovieListHome(navController, navBackStackEntry.arguments?.getString("userName"),viewModel, onTriggerMovies = { viewModel.onTriggerEvent(
                            MovieListEvent.NewMovieEvent) })
                    }
                           composable(
                               route = Screen.MovieDetail.route+"/{idMovie}/{action}",
                               arguments = listOf(navArgument("action") { type = NavType.StringType },navArgument("action") { type =NavType.StringType }),
                               enterTransition = {
                                   materialSharedAxisXIn(forward = true, slideDistance = slideDistance)
                               },
                               exitTransition = {
                                   materialSharedAxisXOut(forward = true, slideDistance = slideDistance)
                               },
                               popEnterTransition = {
                                   materialSharedAxisXIn(forward = false, slideDistance = slideDistance)
                               },
                               popExitTransition = {
                                   materialSharedAxisXOut(forward = false, slideDistance = slideDistance)
                               },

                               ) { navBackStackEntry ->
                               val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)

                               val viewModel: MoviesDetailViewModel =viewModel(key="MoviesListViewModel", factory = factory)
                               MovieDetailPage(navBackStackEntry.arguments?.getString("idMovie"),viewModel,navBackStackEntry.arguments?.getString("action"))
                           }
                }

            }
        }

    }
}

//