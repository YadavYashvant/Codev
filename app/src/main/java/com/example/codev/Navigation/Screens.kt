package com.example.codev.Navigation

sealed class Screens(val route: String) {
    object SignInScreen: Screens(route = "signIn_Screen")
    object SignUpScreen: Screens(route = "signUp_Screen")
}
