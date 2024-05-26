package com.example.habittracking.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.OrangeF9
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.PurpleDark

@Composable
fun OnboardingScreen(
    navController: NavController
) {

    var currentOnboarding by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        //onboarding title
        Text(
            text = when (currentOnboarding) {
                1 -> "WELCOME TO MONUMENTAL HABITS"
                2 -> "CREATE NEW HABIT EASILY"
                3 -> "KEEP TRACK OF YOUR PROGRESS"
                else -> "JOIN SUPPORTIVE COMMUNITY"
            },
            fontSize = 28.sp,
            lineHeight = 28.sp,
            fontFamily = KLASIK_FONT_FAMILY,
            color = PurpleDark,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))

        //onboarding img
        Image(
            painter = painterResource(
                when (currentOnboarding) {
                    1 -> R.drawable.onboarding1
                    2 -> R.drawable.onboarding2
                    3 -> R.drawable.onboarding3
                    else -> R.drawable.onboarding4
                }

            ),
            contentDescription = "img",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))

        val onboardingText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = PurpleDark,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("WE CAN ")
            }

            withStyle(
                style = SpanStyle(
                    color = OrangeFC,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("HELP YOU ")
            }

            withStyle(
                style = SpanStyle(
                    color = PurpleDark,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("TO BE A BETTER VERSION OF ")
            }

            withStyle(
                style = SpanStyle(
                    color = OrangeFC,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("YOURSELF.")
            }

        }
        //onboarding text
        Text(
            text = onboardingText,
            lineHeight = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        //onboarding buttons
        if (currentOnboarding != 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //skip btn
                Text(
                    text = "Skip",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark,
                    modifier = Modifier
                        .clickable {
                            currentOnboarding = 4
                        }
                )

                //indicator
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //1
                    Box(
                        modifier = Modifier
                            .size(if (currentOnboarding == 1) 14.dp else 12.dp)
                            .clip(CircleShape)
                            .background(if (currentOnboarding == 1) PurpleDark else OrangeF9)
                            .clickable {
                                currentOnboarding = 1
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    //2
                    Box(
                        modifier = Modifier
                            .size(if (currentOnboarding == 2) 14.dp else 12.dp)
                            .clip(CircleShape)
                            .background(if (currentOnboarding == 2) PurpleDark else OrangeF9)
                            .clickable {
                                currentOnboarding = 2
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    //3
                    Box(
                        modifier = Modifier
                            .size(if (currentOnboarding == 3) 14.dp else 12.dp)
                            .clip(CircleShape)
                            .background(if (currentOnboarding == 3) PurpleDark else OrangeF9)
                            .clickable {
                                currentOnboarding = 3
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    //4
                    Box(
                        modifier = Modifier
                            .size(if (currentOnboarding == 4) 14.dp else 12.dp)
                            .clip(CircleShape)
                            .background(if (currentOnboarding == 4) PurpleDark else OrangeF9)
                            .clickable {
                                currentOnboarding = 4
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                //next btn
                Text(
                    text = "Next",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark,
                    modifier = Modifier
                        .clickable {
                            if (currentOnboarding < 4) {
                                currentOnboarding++
                            }
                        }
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.OnboardingScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            }
        }

    }
}