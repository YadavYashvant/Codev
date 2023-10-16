package com.example.codev.presentation.sign_in

import com.google.firebase.auth.AuthResult

data class GoogleSignInState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String? = ""
)
