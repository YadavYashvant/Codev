package com.example.codev.firestore_feature.model

data class Post(
    val name: String = "",
    val branch: String = "",
    val skill: String = "",
    val uid: String? = "",
    var isSaved : Boolean = false,
)
