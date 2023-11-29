package com.example.codev.presentation.screens

import android.app.NotificationChannel
import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R
import com.example.codev.firestore_feature.addtosavedcollections
import com.example.codev.firestore_feature.savedposts
import com.example.codev.firestore_feature.userList
import org.intellij.lang.annotations.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {

    /*
    val valueanim by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1.dp.value,
        targetValue = 16.dp.value,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )*/

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
           /* .verticalScroll(scrollState)*/
    ) {
        Text(text = "Bookmarks", modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally), fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 36.sp)

        LazyColumn(

        ) {
            itemsIndexed(savedposts) { index, item ->
                OutlinedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
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

                        savedposts[index]?.name?.let {
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
                                    fontFamily = com.example.codev.presentation.spacefamily,
                                )

                                IconButton(onClick = { /*TODO*/ }) {

                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        savedposts[index]?.branch?.let {
                            Text(
                                text = it,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontFamily = com.example.codev.presentation.spacefamily,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        savedposts[index]?.skill?.let {
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
                                    fontFamily = com.example.codev.presentation.spacefamily,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        /*userList[index]?.uid?.let {
                            Text(
                                text = "POSTED BY - $it",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontFamily = spacefamily,
                                fontStyle = FontStyle.Italic

                            )
                        }*/

                        savedposts[index]?.uid?.let {
                            Text(
                                text = "POSTED BY - $it",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontFamily = com.example.codev.presentation.spacefamily,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }
        }





        /*Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                onClick = { *//*TODO*//* },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .background(color = Color.Transparent),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = valueanim.dp
                ),
                shape = CardDefaults.shape

            ) {
                Row(
                    Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                ) {
                    IconToggleButton(checked = false, onCheckedChange = { *//*TODO*//* }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Info")
                    }
                    Column {
                        Text(text = "Welcome to Codev", fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Text(text = "Make a new project today to get started!", fontFamily = spacefamily)
                    }
                }
            }
        }*/

    }
}
