package com.example.codev

import BottomNavigation
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.codev.ui.theme.CodevTheme
import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codev.animations.EnterAnimation
import com.example.codev.presentation.screens.AddprojectScreen
import com.example.codev.presentation.screens.HomeScreen
import com.example.codev.presentation.screens.NotificationScreen
import com.example.codev.presentation.screens.ProjectScreen
import com.example.codev.presentation.screens.SettingScreen
import com.google.android.gms.auth.api.identity.Identity


val spacefamily = FontFamily(
    Font(R.font.spacebold/*, FontWeight.Bold*/),
    Font(R.font.spaceregular/*, FontWeight.Light*/),
)


/*@AndroidEntryPoint*/
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodevTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
                                    HomeScreen(navController)
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
                }
            }
        }
    }
}
