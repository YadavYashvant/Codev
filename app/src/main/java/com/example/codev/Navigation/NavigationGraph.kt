package com.example.codev.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codev.presentation.`screens-layout`.LoginScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
        ) {
        composable(route = Screens.SignInScreen.route) {
            LoginScreen()

        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
    }
}