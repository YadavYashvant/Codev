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
import com.example.codev.ui.theme.CodevTheme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codev.firestore_feature.readFromFirebase
import com.example.codev.presentation.daos.UserDao
import com.example.codev.presentation.models.User
import com.example.codev.presentation.profile.ProfileScreen
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.SignInScreen
import com.example.codev.presentation.sign_in.SigninViewmodel
import kotlinx.coroutines.launch


val spacefamily = FontFamily(
    Font(R.font.spacebold/*, FontWeight.Bold*/),
    Font(R.font.spaceregular/*, FontWeight.Light*/),
)

var reading = true

/*@AndroidEntryPoint*/
class MainActivity : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        reading = true
        readFromFirebase()
    }

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            onTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WindowCompat.setDecorFitsSystemWindows(window,false)

            var darkTheme by remember { mutableStateOf(false) }

            CodevTheme(
                darkTheme = darkTheme,
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    if(reading) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LinearProgressIndicator(modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().padding(vertical = 32.dp, horizontal = 8.dp), progress = 0.5f)
                        }
                    }


                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable("sign_in") {
                            val  viewModel = viewModel<SigninViewmodel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if(googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("user_screen")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {result ->
                                    if(result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInwithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSigninSuccessful) {
                                if(state.isSigninSuccessful) {
                                    Toast.makeText(applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("user_screen")
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }

                                    val user = googleAuthUiClient.getSignedInUser()
                                    val userdata = User(
                                        uid = user?.userId,
                                        DisplayName = user?.username,
                                        imageUrl = user?.profilePicture
                                    )

                                    val userDao = UserDao()
                                    userDao.addUser(userdata)
                                }
                            )
                        }
                        composable("user_screen") {
                            ProfileScreen(
                                darkTheme = darkTheme,
                                onThemeUpdated = { darkTheme = !darkTheme },
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(applicationContext, "Signed Out", Toast.LENGTH_LONG).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
