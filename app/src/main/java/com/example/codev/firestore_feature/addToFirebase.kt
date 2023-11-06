package com.example.codev.firestore_feature

import android.content.Context
import com.example.codev.firestore_feature.model.Post
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun addToFirebase(
    name: String,
    branch: String,
    skill: String,
    context: Context
) {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val dbUser: CollectionReference = db.collection("users")
    val users = Post(name, branch, skill)

    dbUser.add(users)
        .addOnSuccessListener { documentReference ->
            android.widget.Toast.makeText(
                context,
                "User added successfully",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
        .addOnFailureListener { e ->
            android.widget.Toast.makeText(
                context,
                "Error adding user",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }

}