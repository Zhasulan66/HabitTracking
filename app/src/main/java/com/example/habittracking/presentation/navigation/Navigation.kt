package com.example.habittracking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracking.presentation.screens.HomeScreen
import com.example.habittracking.presentation.screens.NewHabitScreen
import com.example.habittracking.presentation.screens.ProfileScreen
import com.example.habittracking.presentation.screens.SettingScreen
import com.example.habittracking.presentation.screens.SplashScreen
import com.example.habittracking.presentation.screens.auth.ForgotPasswordScreen
import com.example.habittracking.presentation.screens.auth.LoginScreen
import com.example.habittracking.presentation.screens.auth.OnboardingScreen
import com.example.habittracking.presentation.screens.auth.RegistrationScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()



    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        //SplashScreen
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController
            )
        }

        //OnboardingScreen
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(
                navController = navController
            )
        }

        //LoginScreen
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
            )
        }

        //RegistrationScreen
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(
                navController = navController
            )
        }

        //ForgotPasswordScreen
        composable(route = Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                navController = navController
            )
        }

        //HomeScreen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }

        //ProfileScreen
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController
            )
        }

        //SettingScreen
        composable(route = Screen.SettingScreen.route) {
            SettingScreen(
                navController = navController
            )
        }

        //NewHabitScreen
        composable(route = Screen.NewHabitScreen.route) {
            NewHabitScreen(
                navController = navController
            )
        }


    }
}