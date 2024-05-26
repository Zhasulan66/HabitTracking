package com.example.habittracking.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.ui.theme.OrangeF6
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavController
){

    var userEmail by remember { mutableStateOf("") }
    var isUserEmailIncorrect by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
            .padding(horizontal = 10.dp)
    ){
        Spacer(modifier = Modifier.height(10.dp))
        //back btn
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(PurpleDark.red, PurpleDark.green, PurpleDark.blue, alpha = 0.1f))
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "icon"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "FORGOT YOUR PASSWORD?",
            fontFamily = Contacts.KLASIK_FONT_FAMILY,
            fontSize = 20.sp,
            color = PurpleDark,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.forgot_password_img),
            contentDescription = "img",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            Text(
                text = "Enter your registered email below to receive password reset instruction",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
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
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = OrangeF6,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            //send code btn
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Send Reset Code",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}