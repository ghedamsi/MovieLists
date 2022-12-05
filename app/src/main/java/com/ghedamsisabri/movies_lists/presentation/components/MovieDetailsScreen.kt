package com.ghedamsisabri.movies_lists.presentation.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.ghedamsisabri.movies_lists.domain.model.Movies


@Composable
fun MovieDetailsScreen(movie: Movies) {
    val (fraction, setFraction) = remember { mutableStateOf(1f) }
    // Scrim color
    Surface(color = Color.Black.copy(alpha = 0.32f * (1 - fraction))) {
       /* SharedMaterialContainer(
            key = movie.title,
            screenKey = DetailsScreen,
            isFullscreen = true,
            transitionSpec = MaterialFadeOutTransitionSpec,
            onFractionChanged = setFraction
        ) {
            Surface {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val scope = LocalSharedElementsRootScope.current!!
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w1280" +movie.backdrop_path,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !scope.isRunningTransition) {
                                scope.changeUser(-1)
                            },
                    )

                }
            }
        }*/
    }
}