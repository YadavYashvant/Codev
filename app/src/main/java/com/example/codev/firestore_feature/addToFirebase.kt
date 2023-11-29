package com.example.codev.firestore_feature

import android.content.Context
import com.example.codev.firestore_feature.model.Post
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun addToFirebase(
    name: String,
    branch: String,
    skill: String,
    uid: String?,
    context: Context
) {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val dbUser: CollectionReference = db.collection("users")
    val users = Post(name, branch, skill, uid,)

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

fun addtosavedcollections(
    name: String,
    branch: String,
    skill: String,
    uid: String?,
    context: Context
) {
    val db:FirebaseFirestore = FirebaseFirestore.getInstance()

    val dbsavedpost : CollectionReference = db.collection("savedposts")
    val savedposts = Post(name, branch, skill, uid)
    dbsavedpost.add(savedposts)
        .addOnSuccessListener { documentReference ->
            android.widget.Toast.makeText(
                context,
                "Post Bookmarked successfully",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
        .addOnFailureListener { e ->
            android.widget.Toast.makeText(
                context,
                "Error saving post",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
}

