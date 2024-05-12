package com.example.codev.firestore_feature

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    public fun searchPosts(query: String) {
        viewModelScope.launch {
            loading.value = true
            data.value = repository.searchPostsInFirestore(query)
            Log.d("PostsViewModel", "searchPosts: ${data.value.data}")
            loading.value = false
        }
    }

    var state by mutableStateOf(ActorsScreenState(list = data.value.data ?: listOf()))

    private var searchJob: Job? = null

    fun onAction(userAction: UserAction) {
        when (userAction) {
            is UserAction.TextFieldInput -> {
                state = state.copy(searchText = userAction.text)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    searchPostsInList(searchQuery = userAction.text)
                }
            }
            UserAction.SortIconClicked -> {
                state = state.copy(isSortMenuVisible = true)
            }
            UserAction.SortMenuDismiss -> {
                state = state.copy(isSortMenuVisible = false)
            }
        }
    }

    private fun searchPostsInList(
        searchQuery: String
    ) {
        searchQuery.trim()
        val newList = data.value.data?.filter {
//            it.name.contains(searchQuery, ignoreCase = true) ?: false
            it.skill.contains(searchQuery, ignoreCase = true) ?: false

        }
        state = newList?.let { state.copy(list = it) }!!
        Log.d("PostsViewModel", "searchActorsInList: ${state.list}")
        Log.d("PostsViewModel", "PostsList: ${data.value}")
    }

}

sealed class UserAction {
    data class TextFieldInput(val text: String) : UserAction()
    object SortIconClicked : UserAction()
    object SortMenuDismiss : UserAction()
}

data class ActorsScreenState(
    val searchText: String = "",
    val isSortMenuVisible: Boolean = false,
    val list: List<Post> = listOf(),
)