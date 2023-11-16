package com.example.codev.presentation.screens

import android.app.NotificationChannel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        Text(text = "Bookmarks", modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally), fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .background(color = Color.Transparent)

            ) {
                Row(
                    Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
                ) {
                    IconToggleButton(checked = false, onCheckedChange = { /*TODO*/ }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Info")
                    }
                    Column {
                        Text(text = "Welcome to Codev", fontFamily = spacefamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Text(text = "Make a new project today to get started!", fontFamily = spacefamily)
                    }
                }
            }
        }
    }
}
