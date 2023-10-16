package com.example.codev.presentation.screens

import BottomNavigation
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {


        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                HomeScreen(navController)
            }
            composable("Projects") {
                ProjectScreen()
            }
            composable("Notifications") {
                NotificationScreen()
            }
            composable("Settings") {
                SettingScreen()
            }
            composable("addproject") {
                Text("Add Project",
                    fontFamily = com.example.codev.spacefamily,
                    fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
                    textAlign = TextAlign.Center
                )

                AddprojectScreen()
            }
        }

    }

}