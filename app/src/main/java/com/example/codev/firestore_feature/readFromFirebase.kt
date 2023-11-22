package com.example.codev.firestore_feature

import com.example.codev.firestore_feature.model.Post
import com.example.codev.reading
import com.google.firebase.firestore.FirebaseFirestore

val userList = mutableListOf<Post?>()
val savedposts = mutableListOf<Post?>()
fun readFromFirebase() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    db.collection("users")
        .get()
        .addOnSuccessListener { result ->

            val list = result.documents
            for (d in list) {
                val u = d.toObject(Post::class.java)
                userList.add(u)
            }
            reading = false
        }
        .addOnFailureListener { exception ->
            android.util.Log.w("TAG", "Error getting documents.", exception)
        }
}

fun readsavedFromFirebase() {

        var db: FirebaseFirestore = FirebaseFirestore.getInstance()

        db.collection("savedposts")
            .get()
            .addOnSuccessListener { result ->

                val list = result.documents
                for (d in list) {
                    val u = d.toObject(Post::class.java)
                    savedposts.add(u)
                }
                reading = false
            }
            .addOnFailureListener { exception ->
                android.util.Log.w("TAG", "Error getting documents.", exception)
            }
}