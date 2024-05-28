package com.example.habittracking.presentation.navigation

sealed class Screen(val route: String) {

    data object SplashScreen: Screen("splash_screen")

    data object OnboardingScreen: Screen("onboarding_screen")

    data object LoginScreen: Screen("login_screen")

    data object RegistrationScreen: Screen("registration_screen")

    data object ForgotPasswordScreen: Screen("forgot_password_screen")

    data object VerifyEmailScreen : Screen("verify_email_screen")

    data object VerifyPasswordScreen : Screen("verify_password_screen")
    data object ChangePasswordScreen : Screen("change_password_screen")

    data object HomeScreen: Screen("home_screen")

    data object ProfileScreen: Screen("profile_screen")

    data object SettingScreen: Screen("setting_screen")

    data object NewHabitScreen: Screen("new_habit_screen")

    data object HabitInfoScreen: Screen("habit_info_screen")


}