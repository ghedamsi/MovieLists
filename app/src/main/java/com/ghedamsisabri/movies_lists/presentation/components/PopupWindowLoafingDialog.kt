package com.ghedamsisabri.movies_lists.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.R
import com.ghedamsisabri.movies_lists.presentation.navigation.Screen
import com.ghedamsisabri.movies_lists.presentation.profile.ProfilesListViewModel

@Composable
fun PopupWindowLoafingDialog(
    viewModelProfiles: ProfilesListViewModel,
    navController: NavHostController,
    onDismiss: () -> Unit
) {
    val strokeWidth = 5.dp

    val buttonTitle = remember {
        mutableStateOf("Valider")
    }
    if( viewModelProfiles.insert.value!=-1L){
        onDismiss()
        navController.navigate(Screen.ListHome.route+"/${viewModelProfiles.userName.value}")
    }
    val progressAnimate by animateFloatAsState(
        targetValue = 0f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    // on the below line we are creating a column
        Box {
            // on below line we are specifying height and width
            val popupWidth = 300.dp
            val popupHeight = 160.dp

            buttonTitle.value = "Valider"
            // on below line we are adding pop up
            Dialog(
                onDismissRequest = {
                onDismiss ()
            }
            ) {

                Box(
                    Modifier
                        .size(popupWidth, popupHeight)
                        .padding(top = 5.dp)
                        // on below line we are adding background color
                        .background(Color.White, RoundedCornerShape(10.dp))
                        // on below line we are adding border.
                        .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))

                ) {

                    // on below line we are adding column
                    Column(
                        // on below line we are adding modifier to it.
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // on below line we are adding text for our pop up
                        Box(

                            modifier = Modifier
                                .width(
                                    50.dp
                                )
                                .align(Alignment.End)
                                .height(35.dp)
                                .clickable {

                                }
                                .padding(2.dp)
                                .aspectRatio(1f)
                                .clickable {
                                    onDismiss()
                                }
                                .background(Color(0xFF040722), shape = CircleShape),

                            contentAlignment = Alignment.Center
                        ) {
                            Image(painterResource(id = R.drawable.close_icon), "menu")
                        }
                        Text(
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,

                            // on below line we are specifying text
                            text = "CRÉER UN COMPTE",
                            // on below line we are specifying color.
                            color = Color(0xFF040722),
                            // on below line we are adding padding to it
                            modifier = Modifier
                                .align(alignment = Alignment.Start)
                                .padding(vertical = 6.dp, horizontal = 2.dp),
                            // on below line we are adding font size.
                            fontSize = 18.sp
                        )


                        CircularProgressIndicator(
                            modifier = Modifier.drawBehind {
                                drawCircle(
                                    Color(0xFF040722),
                                    radius = size.width / 2 - strokeWidth.toPx() / 2,
                                    style = Stroke(strokeWidth.toPx())
                                )
                            },
                            color = Color.LightGray,
                            strokeWidth = strokeWidth
                        )
                    }
                }
            }
        }


}