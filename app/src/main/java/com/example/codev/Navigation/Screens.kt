package com.example.codev.Navigation

sealed class Screens(val route: String) {
    object SignInScreen: Screens(route = "signIn_Screen")
    object SignUpScreen: Screens(route = "signUp_Screen")

    object HomeScreen: Screens(route = "home_Screen")
    object ProjectScreen: Screens(route = "project_Screen")
    object SettingScreen: Screens(route = "setting_Screen")

    object MainScreen: Screens(route = "main_screen")
}
