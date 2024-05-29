package com.example.codev.firestore_feature

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val queryPostsByName: Query
) {

    suspend fun getPostsFromFirestore(): DataOrException<List<Post>, Exception> {
        val dataOrException = DataOrException<List<Post>, Exception>()

        try {
            dataOrException.data = queryPostsByName.get().await().map { document->
                document.toObject(Post::class.java)
            }
        }catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun getMyPostsFromFirestore(): DataOrException<List<Post>, Exception> {
        val dataOrException = DataOrException<List<Post>, Exception>()
        val userId = FirebaseAuth.getInstance().currentUser?.displayName

        try {
            dataOrException.data = queryPostsByName.get().await().mapNotNull { document ->
                val post = document.toObject(Post::class.java)
                Log.d("PostsRepository", "getMyPostsFromFirestore: ${post.uid} $userId")
                if (post.uid == userId) post else null
            }
        } catch (e: FirebaseFirestoreException) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun searchPostsInFirestore(query: String): DataOrException<List<Post>, Exception> {
        val dataOrException = DataOrException<List<Post>, Exception>()

        try {
            dataOrException.data = queryPostsByName
                .whereEqualTo("name", query)
                .get()
                .await()
                .map { document -> document.toObject(Post::class.java) }
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

}