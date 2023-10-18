package com.example.codev.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.codev.R
import com.example.codev.presentation.sign_in.UserData
import spacefamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userData: UserData?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            /*.padding(bottom = 66.dp, top = 66.dp)*/
    ) {

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 16.dp)
        ) {

            var value by remember { mutableStateOf("") }
            val onValueChange: (String) -> Unit = { value = it }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    shape = MaterialTheme.shapes.extraLarge,
                    placeholder = {
                        Text(
                            text = "Search Projects",
                            Modifier.align(Alignment.CenterVertically),
                            fontFamily = com.example.codev.spacefamily,
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(330.dp)
                    ,
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search, contentDescription = null,
                            Modifier
                                .scale(1.3F)
                                .padding(10.dp)
                        )
                    },
                )

                if(userData?.profilePicture != null) {
                    AsyncImage(
                        model = userData?.profilePicture,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(2.dp)
                            .padding(start = 8.dp)
                            .height(55.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            OutlinedCard(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        fontFamily = spacefamily,
                        text = "Codev", fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(

                        text = "A collaborative space for developers " +
                                "to find and list their undergoing projects " +
                                "and ideas to be worked on and seek for team " +
                                "members with requirements.",
                        fontFamily = spacefamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.concept),
                        contentDescription = "project image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .align(Alignment.Start)
                            .clip(shape = MaterialTheme.shapes.extraLarge)
                            .background(color = Color.Cyan)
                        /*.background(color = Color.LightGray)*/,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                        ) {
                            Text(
                                text = "View Project",
                                fontFamily = spacefamily,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "info",
                                Modifier.padding(end = 5.dp)
                            )
                            Text(
                                text = "Inquire",
                                fontFamily = spacefamily, /*fontWeight = FontWeight.Bold*/
                            )
                        }
                    }
                }
            }
            OutlinedCard(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Notesy",
                        fontFamily = spacefamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "A collaborative space for developers " +
                                "to find and list their undergoing projects " +
                                "and ideas to be worked on and seek for team " +
                                "members with requirements.",
                        fontFamily = spacefamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.concept),
                        contentDescription = "project image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "View Project",
                            fontFamily = spacefamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            /*OutlinedCard(
                onClick = { *//*TODO*//* },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(text = "Notesy",
                        fontFamily = spacefamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(text ="A collaborative space for developers " +
                            "to find and list their undergoing projects " +
                            "and ideas to be worked on and seek for team " +
                            "members with requirements.",
                        fontFamily = spacefamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                    Image(painter = painterResource(id = R.drawable.concept),
                        contentDescription = "project image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Button(
                        onClick = { *//*TODO*//* },
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "View Project", fontFamily = spacefamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
            OutlinedCard(
                onClick = { *//*TODO*//* },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(text = "Notesy",
                        fontFamily = spacefamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(text ="A collaborative space for developers " +
                            "to find and list their undergoing projects " +
                            "and ideas to be worked on and seek for team " +
                            "members with requirements.",
                        fontFamily = spacefamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)

                    )
                    Image(painter = painterResource(id = R.drawable.concept),
                        contentDescription = "project image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Button(
                        onClick = { *//*TODO*//* },
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "View Project", fontFamily = spacefamily, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }*/

        }


        ExtendedFloatingActionButton(
            onClick = {
                      navController.navigate("addproject")
            },
            modifier = Modifier
                .padding(bottom = 100.dp, end = 16.dp)
                .align(Alignment.BottomEnd)

        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add project", Modifier.padding(end = 5.dp))
            Text(text = "Add Project", fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 18.sp, )
        }

    }
}