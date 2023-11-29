package com.example.codev.firestore_feature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostsRepository
): ViewModel() {
    var loading = mutableStateOf(false)
    val data: MutableState<DataOrException<List<Post>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )

    init {
        getPosts()
        getsavedPosts()
    }

    private fun getsavedPosts() {
        viewModelScope.launch {
            readsavedFromFirebase()
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            loading.value = true
            data.value = repository.getPostsFromFirestore()
            loading.value = false
        }
    }
}