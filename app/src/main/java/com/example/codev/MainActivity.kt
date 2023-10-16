package com.example.codev

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
import com.example.codev.Navigation.NavigationGraph
import com.example.codev.ui.theme.CodevTheme
import dagger.hilt.android.AndroidEntryPoint

val spacefamily = FontFamily(
    Font(R.font.spacebold/*, FontWeight.Bold*/),
    Font(R.font.spaceregular/*, FontWeight.Light*/),
)


/*private val googleAuthUiClient by lazy {
    GoogleAuthUiClient(
        context = applicationContext,
        onTapClient = Identity.getSignInClient(applicationContext)
    )
}*/

@AndroidEntryPoint
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

                    NavigationGraph()


                    /*val navController = rememberNavController()
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

                    }*/
                }
            }
        }
    }
}
