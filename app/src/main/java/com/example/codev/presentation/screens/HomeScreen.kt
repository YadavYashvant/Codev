package com.example.codev.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.codev.firestore_feature.userList
import com.example.codev.presentation.sign_in.UserData
import com.example.codev.presentation.sign_in.fontfamily
import com.example.codev.presentation.spacefamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
) {

    /*if(userList.isEmpty()) {
        readFromFirebase()
    }*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            /*.padding(bottom = 66.dp, top = 66.dp)*/
    ) {


        /*val scrollState = rememberScrollState()*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                /*.verticalScroll(scrollState)*/
                .padding(vertical = 16.dp)
        ) {
            var value by remember { mutableStateOf("") }
            val onValueChange: (String) -> Unit = { value = it }

            LazyColumn(
                modifier = Modifier.padding(top = 40.dp, bottom = 100.dp)
            ){
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 32.dp)
                            .padding(bottom = 25.dp)
                            .clip(MaterialTheme.shapes.extraLarge)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 25.dp)
                            ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if(userData?.profilePicture != null) {
                                AsyncImage(
                                    model = userData?.profilePicture, contentDescription = null,
                                    modifier = Modifier
                                        .size(125.dp)
                                        .clip(MaterialTheme.shapes.extraLarge),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            if(userData?.username != null) {
                                Text(
                                    text = userData.username,
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = fontfamily
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            OutlinedButton(
                                onClick = onSignOut,
                                modifier = Modifier
                                    ,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                        Text(text = "Sign out")
                    }
                        }
                    }
                    TextField(
                        value = value,
                        onValueChange = onValueChange,
                        shape = MaterialTheme.shapes.extraLarge,
                        placeholder = {
                            Text(
                                text = "Search Projects",
                                fontFamily = com.example.codev.spacefamily,
                                textAlign = TextAlign.Center,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
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
                }

            /*LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {*/
                itemsIndexed(userList) { index, item ->

                    OutlinedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp)
                    ) {

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentHeight()
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))

                            userList[index]?.name?.let {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                        .align(Alignment.CenterHorizontally),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp),
                                        fontFamily = spacefamily,
                                    )

                                    /*var opendeletedialog by remember {
                                        mutableStateOf(false)
                                    }

                                    IconButton(onClick = {
                                        opendeletedialog = true
                                    }) {
                                        if(opendeletedialog) {
                                            com.example.crud_compose_firestore.presentation.ui.Dialog(index, userList)
                                        }
                                        Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                                    }*/
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            userList[index]?.branch?.let {
                                Text(
                                    text = it,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                        .align(Alignment.CenterHorizontally),
                                    fontFamily = spacefamily,
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            userList[index]?.skill?.let {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .background(
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(40.dp)
                                        )
                                        .align(Alignment.CenterHorizontally),

                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                            .align(Alignment.CenterHorizontally)
                                        ,
                                        fontFamily = spacefamily,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))


                    }
                }
            }
            }

        }
        ExtendedFloatingActionButton(
            onClick = {
                      navController.navigate("addproject")
            },
            modifier = Modifier
                .padding(bottom = 120.dp, end = 32.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add project", Modifier.padding(end = 5.dp).scale(1.5F))
           /* Text(text = "Add Project", fontFamily = com.example.codev.presentation.getSpacefamily, fontWeight = FontWeight.Bold, fontSize = 18.sp, )*/
        }

    }
}
