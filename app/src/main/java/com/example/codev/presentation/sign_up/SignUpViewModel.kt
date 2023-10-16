package com.example.codev.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codev.data.AuthRepository
import com.example.codev.presentation.sign_in.SignInStatecustom
import com.example.codev.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signUpState = Channel<SignInStatecustom>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUpState.send(SignInStatecustom(isSuccess = "Sign Up Success "))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignInStatecustom(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(SignInStatecustom(isError = result.message!!))
                }
            }
        }
    }

}