package com.example.habittracking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eduventure.presentation.screens.auth.ChangePasswordScreen
import com.example.eduventure.presentation.screens.auth.VerifyPasswordScreen
import com.example.habittracking.presentation.screens.HabitInfoScreen
import com.example.habittracking.presentation.screens.HomeScreen
import com.example.habittracking.presentation.screens.NewHabitScreen
import com.example.habittracking.presentation.screens.ProfileScreen
import com.example.habittracking.presentation.screens.SettingScreen
import com.example.habittracking.presentation.screens.SplashScreen
import com.example.habittracking.presentation.screens.auth.ForgotPasswordScreen
import com.example.habittracking.presentation.screens.auth.LoginScreen
import com.example.habittracking.presentation.screens.auth.OnboardingScreen
import com.example.habittracking.presentation.screens.auth.RegistrationScreen
import com.example.habittracking.presentation.screens.auth.VerifyEmailScreen

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

        //VerifyEmailScreen
        composable(route = Screen.VerifyEmailScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            VerifyEmailScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString()
            )
        }

        //ForgotPasswordScreen
        composable(route = Screen.ForgotPasswordScreen.route){
            ForgotPasswordScreen(navController = navController)
        }

        //VerifyPasswordScreen
        composable(route = Screen.VerifyPasswordScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ){ entry ->
            VerifyPasswordScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString(),
            )
        }

        //ChangePasswordScreen
        composable(route = Screen.ChangePasswordScreen.route + "/{email}" + "/{code}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("code"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ){ entry ->
            ChangePasswordScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString(),
                code = entry.arguments!!.getString("code").toString()
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

        //HabitInfoScreen
        composable(route = Screen.HabitInfoScreen.route + "/{habitId}",
            arguments = listOf(
                navArgument("habitId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            HabitInfoScreen(
                navController = navController,
                habitId = entry.arguments!!.getInt("habitId")
            )
        }


    }
}