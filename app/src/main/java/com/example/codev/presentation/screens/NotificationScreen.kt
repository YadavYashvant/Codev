package com.example.codev.presentation.screens

import android.app.NotificationChannel
import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {

    val valueanim by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1.dp.value,
        targetValue = 16.dp.value,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

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
                    .background(color = Color.Transparent),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = valueanim.dp
                ),
                shape = CardDefaults.shape

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
        
        var showsheet by remember { mutableStateOf(false) }
        /*
        if(showsheet) {
            BottomSheet() {
                showsheet = false
            }
        }*/
    }
}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CountryList()
    }
}*/
