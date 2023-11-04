package com.example.codev.presentation.profile

import BottomNavigation
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codev.R
import com.example.codev.animations.EnterAnimation
import com.example.codev.presentation.screens.AddprojectScreen
import com.example.codev.presentation.screens.HomeScreen
import com.example.codev.presentation.screens.NotificationScreen
import com.example.codev.presentation.screens.ProjectScreen
import com.example.codev.presentation.screens.SettingScreen
import com.example.codev.presentation.sign_in.UserData


val fontfamily = FontFamily(
    Font(R.font.signikabold, FontWeight.Bold)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                EnterAnimation {
                    HomeScreen(navController, userData)
                }
            }
            composable("Projects") {
                EnterAnimation {
                    ProjectScreen()
                }

            }
            composable("Notifications") {
                EnterAnimation {
                    NotificationScreen()
                }
            }
            composable("Settings") {
                EnterAnimation {
                    SettingScreen()
                }
            }
            composable("addproject") {
                Text("Add Project",
                    fontFamily = com.example.codev.spacefamily,
                    fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp),
                    textAlign = TextAlign.Center
                )

                EnterAnimation {
                    AddprojectScreen()
                }
            }
        }

    }

    /*
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 32.dp, vertical = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(userData?.profilePicture != null) {
                AsyncImage(
                    model = userData?.profilePicture, contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            if(userData?.username != null) {
                Text(
                    text = userData.username,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontfamily
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            OutlinedButton(onClick = onSignOut) {
                Text(text = "Sign out")
            }
        }
    }
    */
}