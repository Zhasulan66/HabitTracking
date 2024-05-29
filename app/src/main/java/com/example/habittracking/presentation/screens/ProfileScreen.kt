package com.example.habittracking.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.HabitEntry
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.domain.model.auth.UserResponse
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val savedToken: String? by viewModel.readToken().collectAsState(initial = null)
    val userState by viewModel.userState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeWhite)
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //back btn
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            Color(
                                PurpleDark.red,
                                PurpleDark.green,
                                PurpleDark.blue,
                                alpha = 0.1f
                            )
                        )
                        .clickable {
                            navController.popBackStack()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "icon"
                    )
                }

                //screen title
                Text(
                    text = stringResource(id = R.string.profile),
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )

                //edit btn
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            Color(
                                PurpleDark.red,
                                PurpleDark.green,
                                PurpleDark.blue,
                                alpha = 0.1f
                            )
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "icon"
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            when (userState) {
                is Resource.Loading -> {
                    LoadingScreen()
                }

                is Resource.Error -> {
                    ErrorScreen(
                        modifier = Modifier,
                        retryAction = {
                            navController.navigate(
                                route = navController.currentDestination?.route ?: ""
                            ) {
                                popUpTo(
                                    navController.previousBackStackEntry?.destination?.id
                                        ?: return@navigate
                                )
                            }
                        }
                    )
                }

                is Resource.Success -> {

                    val userResponse = (userState as Resource.Success<UserResponse>).data
                    ProfileSuccess(userResponse)

                }

                is Resource.Initial -> {
                    savedToken?.let {
                        viewModel.fetchUserByToken(it)
                    }

                }
            }


        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            onAddClick = {
                navController.navigate(Screen.NewHabitScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            onSettingClick = {
                navController.navigate(Screen.SettingScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 3,
        )
    }
}

@Composable
fun ProfileSuccess(
    user: UserResponse
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profile_img),
                contentDescription = "img",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
            ) {
                Text(
                    text = user.username,
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
                Text(
                    text = stringResource(id = R.string.name),
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

    //billing methods
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profile_billing_icon),
                contentDescription = "img"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.billing_methods),
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark
            )
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "icon",
            tint = PurpleDark,
            modifier = Modifier.clickable { }
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    //longest streak
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profile_streak_icon),
                contentDescription = "img"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.longest_streak),
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.days20),
                fontSize = 16.sp,
                fontFamily = MANROPE_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                color = PurpleDark
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "icon",
                tint = PurpleDark,
                modifier = Modifier.clickable { }
            )
        }

    }

}