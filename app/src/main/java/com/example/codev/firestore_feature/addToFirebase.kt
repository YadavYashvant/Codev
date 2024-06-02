package com.example.codev.firestore_feature

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.example.codev.R
import com.example.codev.firestore_feature.model.Post
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

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

    if(users.branch == "" || users.name == "" || users.skill == "") {
        android.widget.Toast.makeText(
            context,
            "Please fill all fields",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    } else {
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
    val savedpost = Post(name, branch, skill, uid)

    if(savedpost in savedposts){
        MotionToast.darkToast(
            context as Activity,
            "Tough luck mate 😔",
            "Post already saved",
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(context, R.font.rubik_regular))
    } else {
        dbsavedpost.add(savedpost)
            .addOnSuccessListener { documentReference ->
                MotionToast.darkToast(
                    context as Activity,
                    "Post ✉\uFE0F",
                    "Post saved successfully!",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(context, R.font.rubik_regular))
                /*android.widget.Toast.makeText(
                    context,
                    "Post saved successfully",
                    android.widget.Toast.LENGTH_SHORT
                ).show()*/
            }
            .addOnFailureListener { e ->
                MotionToast.darkToast(
                    context as Activity,
                    "Error ❌",
                    "Error saving post",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(context, R.font.rubik_regular))
                /*android.widget.Toast.makeText(
                    context,
                    "Error saving post",
                    android.widget.Toast.LENGTH_SHORT
                ).show()*/
            }
    }
}

/*fun addtosavedcollections(
    name: String,
    branch: String,
    skill: String,
    uid: String?,
    context: Context,
    isSaved: Boolean
) {
    val db:FirebaseFirestore = FirebaseFirestore.getInstance()

    val dbsavedpost : CollectionReference = db.collection("savedposts")
    val savedposts = Post(name, branch, skill, uid)

    if(!isSaved) {
        dbsavedpost.add(savedposts)
            .addOnSuccessListener { documentReference ->
                MotionToast.darkToast(
                    context as Activity,
                    "Post ✉\uFE0F",
                    "Post saved successfully!",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(context, R.font.rubik_regular))
                *//*android.widget.Toast.makeText(
                    context,
                    "Post saved successfully",
                    android.widget.Toast.LENGTH_SHORT
                ).show()*//*
            }
            .addOnFailureListener { e ->
                MotionToast.darkToast(
                    context as Activity,
                    "Error ❌",
                    "Error saving post",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(context, R.font.rubik_regular))
                *//*android.widget.Toast.makeText(
                    context,
                    "Error saving post",
                    android.widget.Toast.LENGTH_SHORT
                ).show()*//*
            }
    } else {
        android.widget.Toast.makeText(
            context,
            "Post already saved",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }
}*/

