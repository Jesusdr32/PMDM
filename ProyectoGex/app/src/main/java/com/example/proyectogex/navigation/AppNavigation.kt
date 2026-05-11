package com.example.proyectogex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectogex.domain.SessionManager
import com.example.proyectogex.screens.LoginScreen
import com.example.proyectogex.screens.MainScreen
import com.example.proyectogex.viewmodel.LoginViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                loginViewModel,
                onLoginSuccess = {
                navController.navigate(Routes.MAIN) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }

        composable(Routes.MAIN) {
            MainScreen(navController)
        }
    }
}