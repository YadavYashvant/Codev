package com.example.codev.firestore_feature

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class deleteFromFirebase() {
    //to delete data from firebase
    val db = Firebase.firestore
    val usercollection = db.collection("users")
    
}