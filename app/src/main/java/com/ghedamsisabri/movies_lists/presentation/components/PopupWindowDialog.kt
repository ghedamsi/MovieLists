package com.ghedamsisabri.movies_lists.presentation.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.ghedamsisabri.movies_lists.R
import com.ghedamsisabri.movies_lists.displayPopupLoading
import com.ghedamsisabri.movies_lists.presentation.profile.ProfilesListViewModel

@Composable
fun PopupWindowDialog(
    viewModelProfiles: ProfilesListViewModel,
    onDismiss: () -> Unit
) {
    var userFirstName by remember { mutableStateOf("") }
    var userLastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val buttonAlpha = remember { mutableStateOf(0.3f) }

    if (!userFirstName.isNullOrEmpty() && !userFirstName.isNullOrEmpty() && !userFirstName.isNullOrEmpty()) {
        buttonAlpha.value = 1f
    } else {
        buttonAlpha.value = 0.3f
    }

    // on below line we are creating variable for button title
    // and open dialog.

    val buttonTitle = remember {
        mutableStateOf("Valider")
    }


    // on the below line we are creating a column
    Box {
        // on below line we are specifying height and width
        val popupWidth = 300.dp
        val popupHeight = 450.dp

        buttonTitle.value = "Valider"
        // on below line we are adding pop up
        Dialog(
            onDismissRequest = {
                onDismiss()
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
                        .padding(horizontal = 20.dp),

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
                            .height(30.dp)
                            .padding(5.dp)
                            .aspectRatio(1f).clickable {
                                onDismiss()
                            }
                            .background(Color(0xFF040722), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(painterResource(id = R.drawable.close_icon), "menu")
                    }
                    Text(
                        textAlign = TextAlign.Start,

                        // on below line we are specifying text
                        text = "Nom",
                        // on below line we are specifying color.
                        color = Color.Black,
                        // on below line we are adding padding to it
                        modifier = Modifier
                            .align(alignment = Alignment.Start)
                            .padding(vertical = 2.dp, horizontal = 2.dp),
                        // on below line we are adding font size.
                        fontSize = 16.sp
                    )
                    OutlinedTextField(

                        value = userFirstName,


                        onValueChange = {
                            userFirstName = it
                        },
                        label = {
                            Text("userFirstName")

                        }
                    )
                    Text(
                        // on below line we are specifying text
                        textAlign = TextAlign.Start,

                        text = "Prenom",
                        // on below line we are specifying color.
                        color = Color.Black,
                        // on below line we are adding padding to it
                        modifier = Modifier
                            .align(alignment = Alignment.Start)
                            .padding(vertical = 2.dp, horizontal = 2.dp),
                        // on below line we are adding font size.
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        value = userLastName,
                        onValueChange = {
                            userLastName = it
                        },
                        label = { Text("userLastName") }
                    )
                    Text(
                        // on below line we are specifying text
                        textAlign = TextAlign.End,
                        text = "Pseudo",
                        // on below line we are specifying color.
                        color = Color.Black,
                        // on below line we are adding padding to it
                        modifier = Modifier
                            .align(alignment = Alignment.Start)
                            .padding(vertical = 2.dp, horizontal = 2.dp),
                        // on below line we are adding font size.
                        fontSize = 16.sp
                    )
                    OutlinedTextField(
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),

                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("password") }
                    )
                    Button(

                        // on below line we are adding modifier.
                        // and padding to it,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF040722)),

                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(buttonAlpha.value)
                            .padding(top = 32.dp, bottom = 8.dp),

                        // on below line we are adding
                        // on click to our button
                        onClick = {

                            // on below line we are updating
                            // boolean value of open dialog.
                            if (buttonAlpha.value == 1f) {
                                viewModelProfiles.insertProfile(
                                    userFirstName,
                                    userLastName,
                                    password
                                )
                                displayPopupLoading.value = !displayPopupLoading.value

                            }


                            // on below line we are checking if dialog is close
                            onDismiss()
                        }
                    ) {

                        // on the below line we are creating a text for our button.
                        Text(
                            text = buttonTitle.value,
                            modifier = Modifier.padding(3.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}