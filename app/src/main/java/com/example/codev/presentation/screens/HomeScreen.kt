package com.example.codev.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Box {
        Card(
            modifier = Modifier
                /*.fillMaxWidth()
                .fillMaxHeight()*/
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "home screen")
        }
    }
}