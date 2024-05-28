package com.example.habittracking.presentation.screens.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.domain.model.auth.ConfirmCodeRequest
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.screens.ErrorScreen
import com.example.habittracking.presentation.screens.LoadingScreen
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel


@Composable
fun VerifyEmailScreen(
    navController: NavController,
    userEmail: String,
){
    val viewModel = hiltViewModel<MainViewModel>()
    val confirmEmailCodeState by viewModel.confirmEmailCodeState.collectAsState()

    when(confirmEmailCodeState){
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(modifier = Modifier, retryAction = {
                navController.popBackStack()
            })
        }
        is Resource.Success -> {
            Toast.makeText(LocalContext.current, "Regiter succesfully!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.VerifyEmailScreen.route){
                    inclusive = true
                }
            }
            viewModel.confirmCodeSuccess()
        }
        is Resource.Initial -> {
            VerifyEmailField(
                navController = navController,
                userEmail = userEmail,
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyEmailField(
    navController: NavController,
    userEmail: String,
    viewModel: MainViewModel
){
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val userCode = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ){
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(PurpleDark.red, PurpleDark.green, PurpleDark.blue, alpha = 0.1f))
                    .clickable {
                        navController.navigate(Screen.RegistrationScreen.route){
                            popUpTo(Screen.VerifyEmailScreen.route){
                                inclusive = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(108.dp))
        Text(
            text = "Enter verification Code",
            fontFamily = MANROPE_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "we've sent code to your email",
            fontFamily = MANROPE_FONT_FAMILY,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = userEmail,
            fontFamily = MANROPE_FONT_FAMILY,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier= Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ){
            OtpTextField(codeText = userCode) {
            }

            TextField(
                value = userCode.value,
                onValueChange = {
                    if (it.length <= 4) {
                        userCode.value = it
                        if(it.length == 4){
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    } else {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                    Log.d("CODE", "Now: ${userCode.value}")
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
                    //.focusRequester(textFieldRequester)
                    .alpha(0f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                )
            )


        }
        Spacer(modifier = Modifier.height(124.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Send code again",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = PurpleDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {
                        viewModel.confirmEmailCode(ConfirmCodeRequest(userEmail, userCode.value))
                    },
                contentAlignment = Alignment.Center
            ){
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

@Composable
fun OtpTextField(codeText: MutableState<String>, onOtpFieldClick:()->Unit) {

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth(0.6f)
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ) {
            onOtpFieldClick()
        }){
        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() ) codeText.value[0].toString() else "")

        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 2) codeText.value[1].toString() else "")

        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 3) codeText.value[2].toString() else "")

        OtpTextFieldBox(
            text = if (codeText.value.isNotEmpty() && codeText.value.length >= 4) codeText.value[3].toString() else "")

    }

}

@Composable
fun OtpTextFieldBox(text:String) {

    Box(
        modifier = Modifier
            .width(48.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

    }

}