package com.example.codev.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R
import com.google.firebase.auth.FirebaseAuth

val fontfamily = FontFamily(
    Font(R.font.spacebold, FontWeight.Bold)
)

lateinit var auth: FirebaseAuth
@Composable
@Preview
fun LoginScreen() {
    OutlinedCard  (
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
    ) {
        Column {
            Text(
                text = "Register or log in",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontfamily,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Textfield()
            TextfieldEmail()

            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Sign in", Modifier.padding(10.dp))
                }

                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Register", Modifier.padding(10.dp))
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Textfield() {
    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(value = inputvalue.value,
        onValueChange = {inputvalue.value = it},
        placeholder = { Text(text = "Enter user name ")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 10.dp),

        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldEmail() {

    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(value = inputvalue.value,
        onValueChange = {inputvalue.value = it},
        placeholder = { Text(text = "Enter email id ")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 10.dp),

        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}