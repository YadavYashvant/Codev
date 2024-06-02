package com.example.codev.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.codev.R
import com.example.codev.firestore_feature.addtosavedcollections
import com.example.codev.firestore_feature.model.Post
import com.example.codev.firestore_feature.savedposts
import com.example.codev.presentation.spacefamily

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun PostCard(
    post: Post,
    navController: NavController
) {
    val mContext = LocalContext.current
    var isSaved by remember {
        mutableStateOf(false)
    }

    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        onClick = {navController.navigate("ProjectDescription/${post.name}")},
        colors = CardDefaults.outlinedCardColors(
//            containerColor = primaryContainerLight,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            post.name?.let {
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
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            post.branch?.let {
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
            post.skill?.let {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(20.dp)
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
                            .align(Alignment.CenterHorizontally),
                        fontFamily = spacefamily,
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            post.uid?.let {
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
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

            IconButton(onClick = {
                //post.isSaved = true
//                savedposts.add(post)
                isSaved = !isSaved
                addtosavedcollections(
                    post.name!!,
                    post.branch!!,
                    post.skill!!,
                    post.uid,
                    mContext,
//                    isSaved
                )
            }) {

                if (isSaved || post in savedposts){
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_filled),
                        contentDescription = null
                    )
                }
                else {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_outlined),
                        contentDescription = null
                    )
                }
            }

                Button(onClick = {
                    navController.navigate("Chat")
                },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(45.dp)
                ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Icon(painter = painterResource(id = R.drawable.chat), contentDescription = "Inquire", modifier = Modifier.scale(0.6f))
                            Text("Inquire", fontSize = 16.sp, fontFamily = spacefamily)
                        }
                }
        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoroutinesApi
@Composable
fun MyPostCard(
    post: Post,
    navController: NavController
){
    val mContext = LocalContext.current

    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        onClick = {navController.navigate("ProjectDescription/${post.name}")},
        colors = CardDefaults.outlinedCardColors(
//            containerColor = primaryContainerLight,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            post.name.let {
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
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            post.branch?.let {
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
            post.skill.let {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(20.dp)
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
                            .align(Alignment.CenterHorizontally),
                        fontFamily = spacefamily,
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            post.uid?.let {
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
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Spacer(modifier = Modifier.height(8.dp))



            /*Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = {
                    //post.isSaved = true
                    addtosavedcollections(
                        post.name!!,
                        post.branch!!,
                        post.skill!!,
                        post.uid,
                        mContext
                    )

                }) {
                    if (post in savedposts) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_filled),
                            contentDescription = null
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_outlined),
                            contentDescription = null
                        )
                    }
                }

                Button(onClick = {
                    navController.navigate("Chat")
                },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(45.dp)
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Icon(painter = painterResource(id = R.drawable.chat), contentDescription = "Inquire", modifier = Modifier.scale(0.6f))
                        Text("Inquire", fontSize = 16.sp, fontFamily = spacefamily)
                    }
                }
            }*/
        }
    }
}
