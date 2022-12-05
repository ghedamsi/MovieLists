package com.ghedamsisabri.movies_lists.presentation.components

import android.nfc.tech.MifareUltralight
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.*
import com.ghedamsisabri.movies_lists.domain.model.Movies
import com.ghedamsisabri.movies_lists.presentation.movieDetail.ActionDetail
import com.ghedamsisabri.movies_lists.presentation.navigation.Screen
import com.skydoves.landscapist.glide.GlideImage
import com.valentinilk.shimmer.shimmer


@Composable
fun listMoviesView(
    name: String, page: Int, listMovies: List<Movies>? = emptyList<Movies>(),
    onChangeScrollPosition: (Int) -> Unit,
    onTriggerNextPage: () -> Unit,
    navController: NavHostController,
    actionDetail: String,
) {
    Log.e("TAG", "listMoviesView: ${listMovies.isNullOrEmpty()}", )

    if (listMovies.isNullOrEmpty())
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            Text(
                text = name,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
            LazyRow(
                modifier = Modifier.height(180.dp),
            ) {
                items(list.size) { index ->

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 8.dp)
                            .shimmer()
                            .width(120.dp),
                        elevation = 2.dp,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(corner = CornerSize(4.dp))
                    ) {

                    }
                }

            }
        }
    else
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            Text(
                text = name,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                modifier = Modifier.height(180.dp),
                state = rememberLazyGridState(),
            ) {
                items(listMovies.size) { index ->
                    onChangeScrollPosition(index)

                    if ((index + 1) >= (page * MifareUltralight.PAGE_SIZE) ) {
                        onTriggerNextPage()
                    }
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 8.dp)
                            .clickable {
                                navController.navigate(Screen.MovieDetail.route + "/${listMovies[index].id}/$actionDetail")
                                Log.e("TAG", "listMoviesView: ${listMovies[index].id}" )
                            }
                            .width(120.dp),
                        elevation = 2.dp,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(corner = CornerSize(4.dp))
                    ) {
                        GlideImage(

                            imageModel = "https://image.tmdb.org/t/p/w1280" + listMovies[index].backdrop_path
                        )

                    }
                }

            }
        }

}