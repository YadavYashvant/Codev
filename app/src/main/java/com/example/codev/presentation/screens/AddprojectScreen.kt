package com.example.codev.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddprojectScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        val name = remember {
            mutableStateOf("")
        }

        val branch = remember {
            mutableStateOf("")
        }

        val skill = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {

            /*Text(text = "Add Project",
                fontFamily = com.example.codev.spacefamily,
                fontSize = 30.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
                textAlign = TextAlign.Center)*/
            TextField(
                value = name.value,
                shape = MaterialTheme.shapes.large,
                onValueChange = { name.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .height(70.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                label = { Text("Enter your project name ", fontFamily = spacefamily) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = branch.value,
                onValueChange = { branch.value = it },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                label = { Text("Enter description of your project ", fontFamily = spacefamily) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = skill.value,
                onValueChange = { skill.value = it },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                label = { Text("Enter skills required for your project ", fontFamily = spacefamily) }
            )
            OutlinedCard(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 8.dp)
                    .background(color = Color.Transparent),

            ) {
                Text(text = "Add relevant project images",
                    fontFamily = spacefamily,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center

                    )
                Image(
                    painter = painterResource(id = R.drawable.concept),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        FloatingActionButton(
            onClick = {  },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 100.dp, end = 20.dp)
        ) {
            Icon(imageVector = Icons.Filled.Check, contentDescription =null)
        }

    }
}