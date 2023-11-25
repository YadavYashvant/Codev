package com.example.codev.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codev.firestore_feature.model.Post
import kotlinx.coroutines.launch

class DataViewModel: ViewModel() {

    val state = mutableStateOf(Post())

    private fun getPost() {
        viewModelScope.launch {
            val post = Post()
            state.value = post
        }
    }

}