package com.example.codev.presentation.sign_in


import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.codev.Navigation.Screens
import com.example.codev.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

val spacefamily = FontFamily(
    Font(R.font.spacebold, FontWeight.Bold),
    Font(R.font.spaceregular, FontWeight.Light),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignInClick: () -> Unit,
    navController: NavController
) {

    val googleSignInState = viewModel.googleState.value



    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Box(
        Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 25.dp)
        ) {
            Text(text = "CODEV",
                textAlign = TextAlign.Center,
                fontFamily = spacefamily,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.concept),
                contentDescription = "project image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }



        OutlinedCard(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.Center)

        ) {
            Column(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 30.dp),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter your credentials",
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    fontFamily = spacefamily
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                    ,
                    colors = TextFieldDefaults.textFieldColors(
                        /*containerColor = Color.Cyan,*/
                        cursorColor = Color.Black,
                        /*disabledLabelColor = Color.Transparent, */unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ), shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                        Text(text = "Email")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        /*backgroundColor = lightBlue,*/
                        cursorColor = Color.Black,
                        /*disabledLabelColor = lightBlue, */unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ), shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                        Text(text = "Password")
                    }
                )

                Button(
                    onClick = {
                        scope.launch {
                            viewModel.loginUser(email, password, navController)

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(
                        text = "Sign In",
                        color = Color.White,
                        modifier = Modifier.padding(7.dp),
                        fontFamily = spacefamily,
                        fontSize = 18.sp,
                    )
                }


                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    if (state.value?.isLoading == true) {
                        CircularProgressIndicator()
                    }

                }
                Text(
                    text = "New User? Sign Up ",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = spacefamily,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Button(
                    onClick = {

                        navController.navigate(Screens.SignUpScreen.route)

                        /*scope.launch {
                            viewModel.loginUser(email, password)
                        }*/
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 50.dp, end = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(text = "Sign Up",
                        color = Color.White,
                        modifier = Modifier.padding(7.dp),
                        fontFamily = spacefamily,
                        fontSize = 18.sp
                    )
                }

                Text(text = "or connect with",
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 20.dp),

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = onSignInClick,

                        /*{


                        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .requestIdToken(context.getString(R.string.client_id))
                            .build()

                        val googleSingInClient = GoogleSignIn.getClient(context, gso)

                        launcher.launch(googleSingInClient.signInIntent)

                    }*/) {
                        Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Facebook Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                    LaunchedEffect(key1 = state.value?.isSuccess) {
                        scope.launch {
                            if (state.value?.isSuccess?.isNotEmpty() == true) {
                                val success = state.value?.isSuccess
                                Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    LaunchedEffect(key1 = state.value?.isError) {
                        scope.launch {
                            if (state.value?.isError?.isNotEmpty() == true) {
                                val error = state.value?.isError
                                Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    LaunchedEffect(key1 = googleSignInState.success) {
                        scope.launch {
                            if (googleSignInState.success != null) {
                                Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    if (googleSignInState.loading){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}