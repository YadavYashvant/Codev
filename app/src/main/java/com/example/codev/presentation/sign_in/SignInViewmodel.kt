package com.example.codev.presentation.sign_in


import androidx.lifecycle.ViewModel
import com.example.codev.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.codev.utils.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signInState = Channel<SignInStatecustom>()
    val signInState = _signInState.receiveAsFlow()

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }


        }
    }


    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInStatecustom(isSuccess = "Sign In Success "))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInStatecustom(isLoading = true))
                }
                is Resource.Error -> {

                    _signInState.send(SignInStatecustom(isError = result.message))
                }
            }

        }
    }
}




/*
class SigninViewmodel: ViewModel() {

    private val _state = MutableStateFlow(SigninState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SigninResult) {
        _state.update {it.copy(
            isSigninSuccessful = result.data != null,
            signInError = result.errorMessage
        )

        }
    }

    fun resetState() {
        _state.update { SigninState() }
    }
}*/
