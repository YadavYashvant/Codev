package com.example.codev.presentation.profile

import com.example.codev.presentation.BottomNavigation
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codev.R
import com.example.codev.animations.EnterAnimation
import com.example.codev.chat_feature.model.ChatUiModel
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.PostsViewModel
import com.example.codev.firestore_feature.model.Post
import com.example.codev.presentation.screens.AddprojectScreen
import com.example.codev.chat_feature.ui.ChatScreen
import com.example.codev.chat_feature.viewmodel.ChatViewModel
import com.example.codev.presentation.screens.HomeScreen
import com.example.codev.presentation.screens.NotificationScreen
import com.example.codev.presentation.screens.ProjectDescriptionScreen
import com.example.codev.presentation.screens.ProjectScreen
import com.example.codev.presentation.screens.SettingScreen
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.UserData


val fontfamily = FontFamily(
    Font(R.font.signikabold, FontWeight.Bold)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    darkTheme: Boolean,
    userData: UserData?,
    onThemeUpdated: () -> Unit,
    onStatusBarColorChange: (color: Color) -> Unit,
    postsviewModel: PostsViewModel,
    dataOrException: DataOrException<List<Post>, Exception>,
    googleAuthUiClient: GoogleAuthUiClient,
    navController_par: NavHostController,
    chatviewModel: ChatViewModel,
    mydataOrException: DataOrException<List<Post>, Exception>
) {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val conversation = chatviewModel.conversation.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)
                EnterAnimation {
                    HomeScreen(navController, navController_par , userData, googleAuthUiClient, postsviewModel, dataOrException)
                }
            }

            composable("Chat") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)

                    ChatScreen(navController,
                        userData,
                        googleAuthUiClient,
                        postsviewModel,
                        dataOrException,
                        model = ChatUiModel(
                            messages = conversation.value,
                            addressee = ChatUiModel.Author.bot
                        ),
                        onSendChatClickListener = { msg -> chatviewModel.sendChat(msg) },
                        modifier = Modifier)
            }

            composable("ProjectDescription/{postId}"){
                    val postId = it.arguments?.getString("postId")
                    val post = dataOrException.data?.find { it.name == postId }
                    ProjectDescriptionScreen(post = post!!, navController = navController)
            }

            composable("Projects") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)
                EnterAnimation {
                    ProjectScreen(postsviewModel, navController, mydataOrException)
                }

            }
            composable("Bookmarks") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)
                EnterAnimation {
                    NotificationScreen()
                }
            }
            composable("Settings") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)
                EnterAnimation {
                    SettingScreen(darkTheme, onThemeUpdated)
                }
            }
            
            composable("addproject") {
                onStatusBarColorChange(MaterialTheme.colorScheme.background)
                Text("Add Project",
                    fontFamily = com.example.codev.spacefamily,
                    fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp),
                    textAlign = TextAlign.Center
                )

                EnterAnimation {
                    AddprojectScreen(navController, userData)
                }
            }
        }
    }
}