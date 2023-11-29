package com.example.codev.firestore_feature.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Post(
    val name: String = "",
    val branch: String = "",
    val skill: String = "",
    val uid: String? = "",
    //var isSaved: Boolean? = false
)
