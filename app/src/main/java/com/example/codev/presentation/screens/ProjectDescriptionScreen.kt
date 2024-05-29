package com.example.codev.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.PostsViewModel
import com.example.codev.firestore_feature.model.Post
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.UserData

@Composable
fun ProjectDescriptionScreen(
    navController: NavHostController,
    userData: UserData?,
    googleAuthUiClient: GoogleAuthUiClient,
    postsviewModel: PostsViewModel,
    dataOrException: DataOrException<List<Post>, Exception>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "More About Project \uD83E\uDDCA")
    }
}