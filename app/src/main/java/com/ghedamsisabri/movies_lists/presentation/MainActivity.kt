package com.ghedamsisabri.movies_lists.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoxWithConstraints {
                val navController = rememberAnimatedNavController()
                val width = constraints.maxWidth / 2
                val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
                       AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.ListProfiles.route

                ) {

                    composable(
                        route = Screen.ListProfiles.route,
                        exitTransition = {

                            slideOutHorizontally(
                                targetOffsetX = { -width },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -width },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeIn(animationSpec = tween(300))
                        },


                        ) { navBackStackEntry ->
                        val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
                        val viewModel: ProfilesListViewModel =viewModel(key="ProfilesListViewModel", factory = factory)
                        MovieListProfiles(navController,viewModel)
                    }
                    composable(
                        route = Screen.ListHome.route+"/{userName}",
                        arguments = listOf(navArgument("userName") { type = NavType.StringType }),
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -width },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeOut(animationSpec = tween(300))
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -width },
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                )
                            ) + fadeIn(animationSpec = tween(300))
                        },

                        ) { navBackStackEntry ->
                        val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)

                        val viewModel: MoviesListViewModel =viewModel(key="ProfilesListViewModel", factory = factory)

                        MovieListHome(navController, navBackStackEntry.arguments?.getString("userName"),viewModel, onTriggerMovies = { viewModel.onTriggerEvent(
                            MovieListEvent.NewMovieEvent) })
                    }
                }

            }
        }

    }
}

//