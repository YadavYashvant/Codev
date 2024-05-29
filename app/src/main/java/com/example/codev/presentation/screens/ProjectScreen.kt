package com.example.codev.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codev.R
import com.example.codev.components.PostCard
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.PostsViewModel
import com.example.codev.firestore_feature.model.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi

val spacefamily = FontFamily(
    Font(R.font.spacebold/*, FontWeight.Bold*/),
    Font(R.font.spaceregular/*, FontWeight.Light*/),
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun ProjectScreen(
    postsviewModel: PostsViewModel,
    navController: NavHostController,
    mydataOrException: DataOrException<List<Post>, Exception>
) {
    val scrollState = rememberScrollState()
    val myposts = mydataOrException.data
    myposts?.let{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .padding(bottom = 80.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontFamily = spacefamily,
                        text = "Projects",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(myposts.size) { index ->
                val post = myposts[index]
                PostCard(post = post, navController = navController)
            }



            /*Card(
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

                    text ="A collaborative space for developers " +
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
                        .background(color = Color.White)
                    *//*.background(color = Color.LightGray)*//*
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Button(
                        onClick = { *//*TODO*//* },
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Text(text = "View Project", fontFamily = spacefamily, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = { *//*TODO*//* },
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "info", Modifier.padding(end = 5.dp))
                        Text(text = "Inquire", fontFamily = spacefamily, *//*fontWeight = FontWeight.Bold*//*)
                    }
                }
            }
        }*/
        }
    }
}