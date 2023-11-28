package com.example.codev.firestore_feature

import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.model.Post
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val queryPostsByName: Query
) {

    suspend fun getPostsFronFirestore(): DataOrException<List<Post>, Exception> {
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



}