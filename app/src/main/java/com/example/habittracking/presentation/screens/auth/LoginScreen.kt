package com.example.habittracking.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.domain.model.auth.TokenResponse
import com.example.habittracking.domain.model.auth.UserLogin
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.screens.ErrorScreen
import com.example.habittracking.presentation.screens.LoadingScreen
import com.example.habittracking.presentation.ui.theme.OrangeF6
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel

@Composable
fun LoginScreen(
    navController: NavController,
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val loginState by viewModel.loginState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()){

        when(loginState){
            is Resource.Initial -> {
                LoginFields(navController, viewModel)
            }
            is Resource.Loading -> {
                LoadingScreen()
            }
            is Resource.Error -> {
                ErrorScreen(modifier = Modifier, retryAction = {
                    navController.navigate(route = navController.currentDestination?.route ?: ""){
                        popUpTo(navController.previousBackStackEntry?.destination?.id ?: return@navigate)
                    }
                })
            }
            is Resource.Success -> {
                val tokenResponse = (loginState as Resource.Success<TokenResponse>).data.authToken
                navController.navigate(Screen.HomeScreen.route){
                    popUpTo(Screen.LoginScreen.route){ inclusive = true }
                }
                viewModel.loginSuccess()
                viewModel.saveToken(tokenResponse)

            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    navController: NavController,
    viewModel: MainViewModel
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isPasswordHide by remember { mutableStateOf(true) }

    var isUserEmailIncorrect by remember { mutableStateOf(false) }
    var isUserPasswordIncorrect by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ){
        Image(
            painter = painterResource(R.drawable.login_bg),
            contentDescription = "img",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "WELCOME TO MONUMENTAL HABITS",
            fontSize = 30.sp,
            lineHeight = 30.sp,
            fontFamily = Contacts.KLASIK_FONT_FAMILY,
            color = PurpleDark,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.TopCenter)
                .offset(y = 60.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp)
        ){
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Log in with email",
                fontSize = 20.sp,
                fontFamily = MANROPE_FONT_FAMILY,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            //email
            TextField(
                value = userEmail,
                onValueChange = { text ->
                    userEmail = text
                },
                textStyle = TextStyle(
                    color = OrangeFD
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = if(isUserEmailIncorrect) Color.Red else Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        fontFamily = MANROPE_FONT_FAMILY,
                        color = Color.Gray,
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "icon",
                        tint = PurpleDark,)
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = OrangeF6,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            //password
            TextField(
                value = userPassword,
                onValueChange = { text ->
                    userPassword = text
                },
                textStyle = TextStyle(
                    color = OrangeFD
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = if(isUserPasswordIncorrect) Color.Red else Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(
                        text = "Password",
                        fontSize = 14.sp,
                        fontFamily = MANROPE_FONT_FAMILY,
                        color = Color.Gray,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (isPasswordHide) PasswordVisualTransformation()
                else VisualTransformation.None,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "icon",
                        tint = PurpleDark,
                        )
                },
                trailingIcon = {
                    Text(
                        text = if (isPasswordHide) "Show" else "Hide",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = PurpleDark,
                        modifier = Modifier
                            .clickable {
                                isPasswordHide = !isPasswordHide
                            }
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = OrangeF6,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            //login btn
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {
                        isUserEmailIncorrect = userEmail.isEmpty()
                        isUserPasswordIncorrect = userPassword.isEmpty()
                        if (!isValidEmail(userEmail)) {
                            isUserEmailIncorrect = true
                        }
                        if (!isPasswordValid(userPassword)) {
                            isUserPasswordIncorrect = true
                        }
                        if (!isUserEmailIncorrect && !isUserPasswordIncorrect) {
                            viewModel.loginUser(UserLogin(userEmail, userPassword))
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Login",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            //forgot password
            Text(
                text = "Forgot Password?",
                fontFamily = MANROPE_FONT_FAMILY,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = PurpleDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.ForgotPasswordScreen.route)
                    },
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            //dont have account
            val doNotHaveAccountText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = PurpleDark,
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 18.sp,
                    )
                ) {
                    append("Don't have an account? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = PurpleDark,
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Sign Up")
                }
            }
            Text(
                text = doNotHaveAccountText,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.RegistrationScreen.route){
                            popUpTo(Screen.LoginScreen.route){
                                inclusive = true
                            }
                        }
                    },
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}