package com.example.codev.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codev.presentation.screens.HomeScreen
import com.example.codev.presentation.screens.LoginScreen
import com.example.codev.presentation.screens.MainScreen
import com.example.codev.presentation.screens.ProjectScreen
import com.example.codev.presentation.screens.SettingScreen
import com.example.codev.presentation.sign_in.SignInScreen
import com.example.codev.presentation.sign_up.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
        ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
    }
}