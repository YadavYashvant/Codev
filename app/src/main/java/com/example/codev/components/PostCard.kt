package com.example.codev.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.codev.R
import com.example.codev.firestore_feature.addtosavedcollections
import com.example.codev.firestore_feature.model.Post
import com.example.codev.firestore_feature.savedposts
import com.example.codev.presentation.spacefamily

@ExperimentalCoroutinesApi
@Composable
fun PostCard(
    post: Post
) {
    val mContext = LocalContext.current

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
        }
    }
}
