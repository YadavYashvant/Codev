package com.example.codev.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.UserData
import com.example.codev.presentation.sign_in.fontfamily
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDialog(
    userData: UserData,
    googleAuthUiClient: GoogleAuthUiClient,
    navController: NavHostController,
    modifier: Modifier,
    skills: MutableState<String>
) {
    val openDialog = remember { mutableStateOf(false) }

    if (userData.profilePicture != null) {
        AsyncImage(
            model = userData.profilePicture,
            contentDescription = null,
            modifier = modifier
                .size(50.dp)
                .clickable { openDialog.value = true }
                .border(4.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(30.dp))
                .clip(MaterialTheme.shapes.extraLarge),
            contentScale = ContentScale.Crop,
        )
    }

    if (openDialog.value) {
        Dialog(
            onDismissRequest = { openDialog.value = false },

        ) {

            val mcontext = LocalContext.current
            val scope = rememberCoroutineScope()
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
//                    .padding(16.dp),
                        ,
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (userData.profilePicture != null) {
                        AsyncImage(
                            model = userData.profilePicture,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(horizontal = 16.dp)
                                .clip(RoundedCornerShape(16.dp))
//                                .clip(MaterialTheme.shapes.extraLarge),
                                    ,
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))

                    if (userData.username != null) {
                        Text(
                            text = userData.username,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontfamily
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier.padding(12.dp),
                        /*colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,

                        )*/
                    ) {
                        Column(){
                            Text(
                                text = "My Skills",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontfamily,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 12.dp),
                                shape = MaterialTheme.shapes.medium,
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedIndicatorColor = Transparent,
                                    focusedIndicatorColor = Transparent,
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                value = skills.value,
                                onValueChange = { newValue ->
                                    skills.value = newValue
                                }
                            )
                            /*Text(
                                text = "Android Developement, Backend Developement, Linux, Spring-Boot",
                                fontSize = 16.sp,
                                fontFamily = fontfamily
                            )*/
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { openDialog.value = false },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Cancel")
                        }
                        TextButton(
                            onClick = {
                                scope.launch {
                                    withContext(NonCancellable) {
                                        googleAuthUiClient.signOut()
                                    }
                                }
                                openDialog.value = false
                                navController.navigate("sign_in")
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Sign Out")
                        }
                    }
                }
            }
        }
    }

}