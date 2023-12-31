package com.example.codev.presentation.daos

import com.example.codev.presentation.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    val db = Firebase.firestore
    val userCollection = db.collection("users")
    fun addUser(user: User?) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                userCollection.document(user.uid.toString()).set(it)
            }
        }
    }
}