package com.example.codev.presentation.screens

import androidx.compose.runtime.Composable

@Composable
fun MyProfileScreen(){
    /*androidx.compose.animation.AnimatedVisibility(
        visible = profilevisible,
        enter = *//*slideInVertically {
                                        // Slide in from 40 dp from the top.
                                        with(density) { -40.dp.roundToPx() }
                                    } *//* expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()


    ) {

        Column(
            Modifier.fillMaxSize().
            clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)).
            padding(horizontal = 12.dp)
                .padding(bottom = 32.dp, top = 45.dp)
        ) {
            Card(
                modifier = Modifier.padding(12.dp)
            ) {
                if (userData != null) {
                    AsyncImage(
                        model = userData.profilePicture,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            //                                                    .clickable { profilevisible = true }
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                        contentScale = ContentScale.Crop,
                    )
                }

                if (userData != null) {
                    if (userData.username != null) {
                        Text(
                            text = userData.username,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontfamily
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(
                        onClick = { profilevisible = false },
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
                            profilevisible = false
                            navController.navigate("sign_in")
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Sign Out")
                    }
                }
            }
        }

    }*/
}