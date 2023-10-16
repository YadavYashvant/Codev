package com.example.codev.presentation.sign_in


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
}