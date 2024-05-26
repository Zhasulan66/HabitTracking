package com.example.habittracking.presentation.screens.auth

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.OrangeF6
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController
){
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isPasswordHide by remember { mutableStateOf(true) }

    var isUserNameIncorrect by remember { mutableStateOf(false) }
    var isUserEmailIncorrect by remember { mutableStateOf(false) }
    var isUserPasswordIncorrect by remember { mutableStateOf(false) }

    var isSignedInChecked by remember { mutableStateOf(true) }
    var isEmailMeChecked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
            .padding(horizontal = 10.dp)
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(R.drawable.register_img),
            contentDescription = "img",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "CREATE YOUR ACCOUNT",
            fontFamily = KLASIK_FONT_FAMILY,
            fontSize = 20.sp,
            color = PurpleDark,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        //userName
        TextField(
            value = userName,
            onValueChange = { text ->
                userName = text
            },
            textStyle = TextStyle(
                color = OrangeFD
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = if(isUserNameIncorrect) Color.Red else Color.White,
                    shape = RoundedCornerShape(10.dp)
                ),
            placeholder = {
                Text(
                    text = "Name Surname",
                    fontSize = 14.sp,
                    fontFamily = MANROPE_FONT_FAMILY,
                    color = Color.Gray,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "icon",
                    tint = PurpleDark,)
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

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
                containerColor = Color.White,
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
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        //keep me signed in
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = isSignedInChecked,
                onCheckedChange = { checked ->
                    isSignedInChecked = checked
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = OrangeFD,
                    uncheckedColor = Color.White,
                    checkmarkColor = PurpleDark
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Keep me signed in",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = isEmailMeChecked,
                onCheckedChange = { checked ->
                    isEmailMeChecked = checked
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = OrangeFD,
                    uncheckedColor = Color.White,
                    checkmarkColor = PurpleDark
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Email me about special pricing and more",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark
            )
        }

        //sign in btn
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(OrangeFD)
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Create Account",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PurpleDark
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        //already have account
        val alreadyHaveAccountText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = PurpleDark,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                )
            ) {
                append("Already have an account? ")
            }
            withStyle(
                style = SpanStyle(
                    color = PurpleDark,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Sign In")
            }
        }
        Text(
            text = alreadyHaveAccountText,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.LoginScreen.route){
                        popUpTo(Screen.RegistrationScreen.route){
                            inclusive = true
                        }
                    }
                },
            textAlign = TextAlign.Center
        )

    }
}

// Function to validate email
internal fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return email.matches(emailPattern)
}

fun isPasswordValid(password: String): Boolean {
    // Check minimum length
    if (password.length < 8) {
        return false
    }

    // Check if password contains numbers
    val containsNumber = password.any { it.isDigit() }
    if (!containsNumber) {
        return false
    }

    // Check if password is entirely numeric
    if (password.all { it.isDigit() }) {
        return false
    }

    // Define common patterns to avoid
    val commonPatterns = listOf("password", "qwerty", "abc", "admin")

    // Check if password contains any common patterns
    if (commonPatterns.any { password.contains(it, ignoreCase = true) }) {
        return false
    }

    // If all criteria pass, return true
    return true
}