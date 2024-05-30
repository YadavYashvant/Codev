package com.example.codev.presentation.screens

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.PostsViewModel
import com.example.codev.firestore_feature.model.Post
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.UserData
import com.example.codev.presentation.sign_in.fontfamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDescriptionScreen(
    navController: NavHostController,
    post: Post
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Project Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 80.dp),
        ){
                Text(text = "More About ${post.name} \uD83E\uDDCA", style = MaterialTheme.typography.displayMedium, fontSize = 36.sp, fontFamily = fontfamily)
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Project Name: ${post.name}", style = MaterialTheme.typography.bodyLarge, fontFamily = fontfamily)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Project Description:  ${post.branch}", style = MaterialTheme.typography.bodyLarge, fontFamily = fontfamily)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Required Tech Stack: ${post.skill}", style = MaterialTheme.typography.bodyLarge, fontFamily = fontfamily)
                Spacer(modifier = Modifier.height(16.dp))
                post.uid?.let { Text(text = "Posted by : $it", style = MaterialTheme.typography.bodyMedium, fontFamily = fontfamily) }

            Button(onClick = {
                navController.navigate("Chat")
            },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 48.dp).padding(horizontal = 48.dp).fillMaxWidth()

            ) {
                Row {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Apply Now", modifier = Modifier.padding(start = 16.dp))
                    Text(text = "Apply Now")
                }

            }
        }
    }
}