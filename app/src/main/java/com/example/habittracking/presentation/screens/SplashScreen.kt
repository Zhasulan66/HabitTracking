package com.example.habittracking.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navController: NavController
){
    val viewModel = hiltViewModel<MainViewModel>()
    val savedToken: String? by viewModel.readToken().collectAsState(initial = null)

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "img",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "WELCOME TO MONUMENTAL HABITS",
            fontSize = 40.sp,
            lineHeight = 40.sp,
            fontFamily = KLASIK_FONT_FAMILY,
            color = PurpleDark,
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .align(Alignment.TopCenter)
                .offset(y = 40.dp),
            textAlign = TextAlign.Center

        )
    }

    // Navigate to the home screen after a delay
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(
            if(savedToken == null)
                Screen.OnboardingScreen.route
            else Screen.HomeScreen.route
        ){
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }

}