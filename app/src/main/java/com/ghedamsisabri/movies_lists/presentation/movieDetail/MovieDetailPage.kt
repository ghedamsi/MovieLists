package com.ghedamsisabri.movies_lists.presentation.movieDetail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghedamsisabri.movies_lists.R
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorAddCircle
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorAddCircleText
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorMainCircleText
import com.ghedamsisabri.movies_lists.presentation.ui.theme.cardBackGroundGray
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailPage(
    movieId: String?,
    viewModel: MoviesDetailViewModel,
    actionDetail: String?,
) {
    movieId?.let {
        viewModel.id.value = it
    }
    actionDetail?.let {
        viewModel.action.value=actionDetail
    }
     viewModel.onTriggerEvent(MovieDetailListEvent.NewMovieDetailListEvent)
     var mMovies = viewModel.mMovie.value

    Log.e("TAG", "MovieDetailPage movieId:${movieId} +$actionDetail  ")
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.backgroun),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        var readMore by remember {
            mutableStateOf(false)
        }
        Card(
            modifier = Modifier

                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, bottom = 64.dp, top = 64.dp),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))

        ) {
            Column() {

                Box(
                    modifier = Modifier

                        .fillMaxWidth()
                        .height(204.dp),
                ) {
                    GlideImage(
                        imageModel = "https://image.tmdb.org/t/p/w1280" + mMovies.backdrop_path
                    )
                    GlideImage(
                        imageModel = R.drawable.cross, modifier = Modifier
                            .align(Alignment.Center)
                            .width(104.dp)
                            .height(104.dp)
                    )
                }
                Row(
                    Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = mMovies.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorAddCircle
                    )
                    Spacer(Modifier.weight(1f))
                    Card(
                        modifier = Modifier
                            .width(31.dp)
                            .padding(top = 16.dp)
                            .height(20.dp)
                            .background(Color.White),
                        border = BorderStroke(1.dp, ColorAddCircle)
                    ) {
                        Text(
                            text = "PG",
                            color = ColorAddCircle,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        text = "Familial, Animation, Aventure, Comédie, Fantastique",
                        fontSize = 13.sp,
                        color = ColorAddCircle,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = mMovies.release_date,
                        fontSize = 13.sp,
                        color = ColorAddCircle
                    )
                }
                Row(
                    Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Card(
                        backgroundColor = ColorMainCircleText,
                        modifier = Modifier
                            .width(120.dp)
                            .height(44.dp)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(corner = CornerSize(4.dp))

                    ) {
                        Row(
                            Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(CenterVertically),

                                painter = painterResource(id = R.drawable.ic_vue_play),
                                contentDescription = "play movie"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .align(CenterVertically),
                                text = "Regarder",
                                color = ColorAddCircleText,
                                fontSize = 13.sp,
                            )
                        }

                    }

                    Card(
                        backgroundColor = cardBackGroundGray,

                        modifier = Modifier
                            .width(52.dp)
                            .height(44.dp)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(corner = CornerSize(4.dp))

                    ) {
                        Image(
                            modifier = Modifier.padding(12.dp),
                            painter = painterResource(id = R.drawable.ic_star_ui),
                            contentDescription = "add to favorite"
                        )


                    }

                }
                Text(
                    modifier = if (readMore) Modifier
                        .wrapContentHeight()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 8.dp,
                            bottom = 4.dp
                        ) else Modifier
                        .height(70.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 4.dp),
                    text = mMovies.overview,
                    color = ColorMainCircleText,
                    fontSize = 13.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 1.dp, bottom = 8.dp)
                        .clickable() {
                            readMore = !readMore
                        },

                    text = "Lire la suite",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorAddCircle
                )
                LazyHorizontalGrid(
                    modifier = Modifier.height(95.dp),
                    rows = GridCells.Fixed(1),
                    state = rememberLazyGridState(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(5) { index ->
                        GlideImage(
                            imageModel = "https://qodeinteractive.com/magazine/wp-content/uploads/2020/09/How-to-Add-Animated-GIFs-in-WordPress.jpg",
                            modifier = Modifier
                                .width(120.dp)
                        )

                    }
                }
            }


        }

    }
}

