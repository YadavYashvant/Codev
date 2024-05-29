package com.example.codev.presentation.sign_in
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R


val fontfamily = FontFamily(
    Font(R.font.signikabold, FontWeight.Bold)
)

@Composable
fun SignInScreen(
    state: SigninState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier.padding(top = 56.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

            Image(
                painter = painterResource(id = R.drawable.codev_signin_logo),
                contentDescription = null,
                modifier = Modifier.wrapContentWidth().clip(RoundedCornerShape(20.dp)).align(Alignment.CenterHorizontally),
            )

        LoginScreen(onLoginClick = { _, _ -> })

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 32.dp),

                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = null)
                    Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google Icon")
                    Text(text = "Sign In With Google", modifier = Modifier.padding(start = 10.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp,
//                        fontFamily = fontfamily
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen( onLoginClick: (String, String) -> Unit) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.padding(16.dp).wrapContentHeight()) {

            OutlinedTextField(value = usernameOrEmail,
                onValueChange = { usernameOrEmail = it },
                label = { Text("Username or Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Button(
                onClick = { onLoginClick(usernameOrEmail, password) },
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Login")
            }
        }

        Text(text = "OR", modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp))
    }
}