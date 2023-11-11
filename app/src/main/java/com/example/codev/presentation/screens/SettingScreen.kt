package com.example.codev.presentation.screens

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codev.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(darkTheme: Boolean, onThemeUpdated: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemeSwitcher(
            darkTheme = darkTheme,
            onClick = onThemeUpdated
        )
        /*Spacer(modifier = Modifier.height(20.dp))
        ThemeSwitcher(
            darkTheme = darkTheme,
            size = 100.dp,
            padding = 5.dp,
            onClick = onThemeUpdated
        )
        Spacer(modifier = Modifier.height(20.dp))
        ThemeSwitcher(
            darkTheme = darkTheme,
            size = 50.dp,
            padding = 5.dp,
            onClick = onThemeUpdated
        )*/
    }

    /*val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        Text(text = "Settings", modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally), fontFamily = com.example.codev.presentation.getSpacefamily, fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedCard(
                onClick = { *//*TODO*//* },
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
                    IconToggleButton(checked = false, onCheckedChange = { *//*TODO*//* }) {
                        Image(painter = painterResource(id = R.drawable.theme), contentDescription = "theme", Modifier.scale(.75F))
                    }
                    Text(text = "Default Theme", fontFamily = com.example.codev.presentation.getSpacefamily, fontSize = 24.sp)

                    ThemeSwitcher(
                        darkTheme = darkTheme,
                        onClick = onThemeUpdated
                    )
                    *//*Button(onClick = { *//**//*TODO*//**//* }) {
                        Icon(imageVector = Icons.Outlined.Check, contentDescription = null, Modifier.padding(end = 3.dp))
                        Text(text = "Light", fontFamily = com.example.codev.presentation.getSpacefamily)
                    }*//*
                }



            }
        }
    }*/
}

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec, label = ""
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable { onClick() }
        .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = R.drawable.nightmode),

                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = R.drawable.lightmode),
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}