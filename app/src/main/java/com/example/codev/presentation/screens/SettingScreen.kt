package com.example.codev.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        Text(text = "Settings", modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally), fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedCard(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .background(color = Color.Transparent)

            ) {
                Row(
                    Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    IconToggleButton(checked = false, onCheckedChange = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.theme), contentDescription = "theme", Modifier.scale(.75F))
                    }
                    Text(text = "Default Theme", fontFamily = spacefamily, fontSize = 24.sp)

                    Button(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.Check, contentDescription = null, Modifier.padding(end = 3.dp))
                        Text(text = "Light", fontFamily = spacefamily)
                    }
                }

            }
        }
    }
}