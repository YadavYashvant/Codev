package com.example.codev.firestore_feature

import androidx.compose.runtime.mutableStateListOf
import com.example.codev.firestore_feature.model.Post
import com.example.codev.reading
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await

val userList = mutableListOf<Post?>()
val savedposts = mutableStateListOf<Post?>()
fun readFromFirebase(): MutableList<Post?> {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    try {
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
    }catch (e: FirebaseFirestoreException){
        android.util.Log.w("TAG", "Error getting documents.", e)
    }

    return userList

    /*db.collection("users")
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
        }*/
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