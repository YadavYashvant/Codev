package com.example.codev.components

//import android.graphics.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomBtn(text : String, color: Color){

    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )

    ) {
        Text(text = text)
    }

}