package com.example.codev.presentation.sign_in

data class SigninState(
    val isSigninSuccessful: Boolean = false,
    val signInError: String? = null
)

data class signinwithemailandpasswordResult (
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
