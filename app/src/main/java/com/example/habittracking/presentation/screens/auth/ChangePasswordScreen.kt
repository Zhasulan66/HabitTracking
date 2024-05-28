package com.example.eduventure.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.domain.model.auth.ResetPasswordRequest
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.screens.ErrorScreen
import com.example.habittracking.presentation.screens.LoadingScreen
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel


@Composable
fun ChangePasswordScreen(
    navController: NavController,
    userEmail: String,
    code: String
){

    val viewModel = hiltViewModel<MainViewModel>()
    val resetPasswordState by viewModel.resetPasswordState.collectAsState()

    when(resetPasswordState){
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(modifier = Modifier, retryAction = {
                navController.popBackStack()
            })
        }
        is Resource.Success -> {
            Toast.makeText(LocalContext.current, "Password changed!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.ChangePasswordScreen.route){
                    inclusive = true
                }
            }
            viewModel.resetPasswordSuccess()
        }
        is Resource.Initial -> {
            ChangePasswordField(
                navController = navController,
                viewModel = viewModel,
                userEmail = userEmail,
                code = code
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordField(
    navController: NavController,
    viewModel: MainViewModel,
    userEmail: String,
    code: String
){
    var userPassword by remember { mutableStateOf("") }
    var userPassword2 by remember { mutableStateOf("") }

    var isPasswordHide by remember { mutableStateOf(true) }
    var isPasswordHide2 by remember { mutableStateOf(true) }

    var isUserPasswordIncorrect by remember { mutableStateOf(false) }
    var isUserPassword2Incorrect by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ) {
        //Back Button
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(PurpleDark.red, PurpleDark.green, PurpleDark.blue, alpha = 0.1f))
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route){
                            popUpTo(Screen.ChangePasswordScreen.route)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Create new password",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(22.dp))

            //Password
            Text(
                text = "Password",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = userPassword,
                onValueChange = { text ->
                    userPassword = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = if (isUserPasswordIncorrect) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
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
                trailingIcon = {
                    Icon(
                        painter = if (isPasswordHide) painterResource(id = R.drawable.baseline_visibility_off_24)
                        else painterResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {
                            isPasswordHide = !isPasswordHide
                        })
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Password2
            TextField(
                value = userPassword2,
                onValueChange = { text ->
                    userPassword2 = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = if (isUserPassword2Incorrect) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                placeholder = {
                    Text(
                        text = "Confirm password",
                        fontSize = 14.sp,
                        fontFamily = MANROPE_FONT_FAMILY,
                        color = Color.Black,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (isPasswordHide2) PasswordVisualTransformation()
                else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = if (isPasswordHide2) painterResource(id = R.drawable.baseline_visibility_off_24)
                        else painterResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {
                            isPasswordHide2 = !isPasswordHide2
                        })
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(22.dp))

            //Continue button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {
                        isUserPasswordIncorrect = userPassword.isEmpty()
                        isUserPassword2Incorrect = userPassword2.isEmpty()
                        if (userPassword != userPassword2) {
                            isUserPasswordIncorrect = true
                            isUserPassword2Incorrect = true
                        }
                        if (!isUserPasswordIncorrect && !isUserPassword2Incorrect) {
                            viewModel.resetPassword(ResetPasswordRequest(code, userEmail, userPassword, userPassword2))
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Continue",
                    fontSize = 20.sp,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}