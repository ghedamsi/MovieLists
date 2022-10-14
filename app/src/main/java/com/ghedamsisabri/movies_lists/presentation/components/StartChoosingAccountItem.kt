package com.ghedamsisabri.movies_lists.presentation.components

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.*
import com.ghedamsisabri.movies_lists.presentation.navigation.Screen
import com.ghedamsisabri.movies_lists.presentation.profile.ProfilesListViewModel
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorAddCircle
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorAddCircleText
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorMainCircle
import com.ghedamsisabri.movies_lists.presentation.ui.theme.ColorMainCircleText

@SuppressLint("UnrememberedMutableState")
@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment


    val horizontal by animateFloatAsState(biased.horizontalBias)
    val vertical by animateFloatAsState(biased.verticalBias)
    return derivedStateOf { BiasAlignment(horizontal, vertical) }
}


@Composable
fun StartChoosingAccountItem(
    name: String,
    navController: NavHostController,
    viewModelProfiles: ProfilesListViewModel
) {

    var visibleItem by remember {
        mutableStateOf(false)
    }

    var fontSizeText   by remember {mutableStateOf(35.sp)}
    var fooItem  by remember { mutableStateOf(Alignment.Center)}
    var displayPopup  by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    val alignment by animateAlignmentAsState(fooItem)
    val bottomBarOffset by animateDpAsState(targetValue = if (visibleItem) 2.dp else 300.dp)
    Box(
        modifier = Modifier
            .border(width = 4.dp, color = Color.Transparent)
            .padding(32.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = if (visibleItem) 24.sp else 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = if (visibleItem) Modifier

                .animateContentSize(
                    animationSpec = tween(durationMillis = 200)
                )
                .padding(14.dp)
            else Modifier
                .animateContentSize(
                    animationSpec = tween(durationMillis = 200)
                )
                .padding(16.dp)

        )
    }
    Column {
        Spacer(modifier = Modifier.height(124.dp))
        if (visibleItem) {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1A2E2B3D),
                                Color(0xFF2E2B3D)
                            )
                        )
                    )
                    .padding(top = 64.dp)
                    .offset(y = bottomBarOffset)
                    .fillMaxWidth()
                    .fillMaxHeight(),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .border(width = 4.dp, color = Color.Transparent)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Premiére visite ?",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                    )

                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = rememberLazyGridState(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(recipesMain.value.size) { index ->
                        var color =ColorMainCircle
                        var textColor = ColorMainCircleText

                        if (recipesMain.value.size - 1 == index) {
                            color = ColorAddCircle
                            textColor = ColorAddCircleText
                        }


                        Box(

                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(24.dp)
                                .background(color, shape = CircleShape) .clickable {
                                    if (index == recipesMain.value.size - 1) {
                                        displayPopup = !displayPopup

                                    }else{
                                        navController.navigate(Screen.ListHome.route+"/${recipesMain.value[index].userName}")
                                    }

                                }
                                .layout(){ measurable, constraints ->
                                    // Measure the composable
                                    val placeable = measurable.measure(constraints)

                                    //get the current max dimension to assign width=height
                                    val currentHeight = placeable.height
                                    val currentWidth = placeable.width
                                    val newDiameter = maxOf(currentHeight, currentWidth)

                                    //assign the dimension and the center position
                                    layout(newDiameter, newDiameter) {
                                        // Where the composable gets placed
                                        placeable.placeRelative((newDiameter-currentWidth)/2, (newDiameter-currentHeight)/2)
                                    }
                                }

                        ) {
                            var userName= recipesMain.value[index].userName.toUpperCase()
                            if(index!= recipesMain.value.size - 1){
                                userName = if(recipesMain.value[index].userName.length>=2){
                                    recipesMain.value[index].userName.substring(0,2).toUpperCase()
                                }else{
                                    recipesMain.value[index].userName.toUpperCase()
                                }
                            }
                            Text(
                                userName,
                                modifier  = Modifier
                                    .clickable {
                                    if (index == recipesMain.value.size - 1) {
                                        displayPopup = !displayPopup

                                    }else{
                                        navController.navigate(Screen.ListHome.route+"/${recipesMain.value[index].userName}")

                                    }

                                },
                                fontSize = 35.sp,
                                color = textColor,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

        }
    }

    if (displayPopup)
        PopupWindowDialog(viewModelProfiles){
            displayPopup=false
        }
    if (displayPopupLoading.value){
        PopupWindowLoafingDialog(viewModelProfiles,navController){
            displayPopupLoading.value=false
        }
    }
    Handler(Looper.getMainLooper()).postDelayed({
        //Do something after 100ms
        fooItem = Alignment.TopCenter
        visibleItem = true

    }, 2000)
    Handler(Looper.getMainLooper()).postDelayed({
        //Do something after 100ms
        fontSizeText = 23.sp
    }, 2500)


}