package com.example.habittracking.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.domain.model.Analytics
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.GrayC8
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HabitInfoScreen(
    navController: NavController,
    habitId: Int
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val habitInfoState by viewModel.habitInfoState.collectAsState()
    val analyticsState by viewModel.analyticsState.collectAsState()

    when (habitInfoState) {
        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Error -> {
            ErrorScreen(
                modifier = Modifier,
                retryAction = {
                    navController.navigate(route = navController.currentDestination?.route ?: ""){
                        popUpTo(navController.previousBackStackEntry?.destination?.id ?: return@navigate)
                    }
                }
            )
        }

        is Resource.Success -> {
            when (analyticsState) {
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
                    val analytics = (analyticsState as Resource.Success<Analytics>).data
                    val habit = (habitInfoState as Resource.Success<Habit>).data
                    HabitInfoSuccess(navController, habit, analytics)
                }

                else -> {}
            }
        }
        else -> {
            viewModel.fetchAnalytics(1)
            viewModel.fetchHabitById(habitId)
        }
    }
}

@Composable
fun HabitInfoSuccess(
    navController: NavController,
    habit: Habit,
    analytics: Analytics
) {
    var showHabitCompleteDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "icon"
                    )
                }

                //screen title
                Text(
                    text = "Habit Info",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
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
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "icon"
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OrangeWhite),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.habit_info_img),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = habit.habitName,
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_repeat),
                            contentDescription = "img"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Repeat everyday",
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_reminder),
                            contentDescription = "img",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Reminders: ${convertTo12HourFormat(habit.reminderTime)}",
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Analytics",
                fontFamily = MANROPE_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleDark,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                //1 2
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "${analytics.longestStreak} days",
                                fontFamily = KLASIK_FONT_FAMILY,
                                fontSize = 24.sp,
                                color = PurpleDark
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Longest Streak",
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_analytics1),
                            contentDescription = "img"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "${analytics.streak} days",
                                fontFamily = KLASIK_FONT_FAMILY,
                                fontSize = 24.sp,
                                color = PurpleDark
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Current Streak",
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_analytics2),
                            contentDescription = "img"
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                //3 4
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "${analytics.completionRate} %",
                                fontFamily = KLASIK_FONT_FAMILY,
                                fontSize = 24.sp,
                                color = PurpleDark
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Completion Rate",
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_analytics3),
                            contentDescription = "img"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(
                                text = "${analytics.averageEasinessScore}",
                                fontFamily = KLASIK_FONT_FAMILY,
                                fontSize = 24.sp,
                                color = PurpleDark
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Average Easiness Score",
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_analytics4),
                            contentDescription = "img"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            //mark complete btn
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(OrangeFD)
                    .clickable {
                        showHabitCompleteDialog = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mark Habit as Complete",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            //mark missed btn
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mark Habit as Missed",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
            }

        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HabitInfoScreen.route) {
                        inclusive = true
                    }
                }
            },
            onAddClick = {
                navController.navigate(Screen.NewHabitScreen.route) {
                    popUpTo(Screen.HabitInfoScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.HabitInfoScreen.route) {
                        inclusive = true
                    }
                }
            },
            onSettingClick = {
                navController.navigate(Screen.SettingScreen.route) {
                    popUpTo(Screen.HabitInfoScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 1,
        )

        //choose reminder dialog
        if (showHabitCompleteDialog) {
            HabitCompleteDialog(
                onDismiss = { showHabitCompleteDialog = false },
                onPositiveClick = {
                    navController.navigate(Screen.NewHabitScreen.route){
                        popUpTo(Screen.HomeScreen.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }

}

@Composable
fun HabitCompleteDialog(
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.6f)
            .background(Color.Black)
            .clickable {
                onDismiss()
            }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        Box(
            modifier = Modifier
                .offset(y = (-20).dp)
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(10.dp))
                .then(modifier)
                .background(Color.White)
                .padding(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.habit_complete_img),
                    contentDescription = "img",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Congratulations!",
                    fontFamily = KLASIK_FONT_FAMILY,
                    fontSize = 24.sp,
                    color = PurpleDark,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Well done! Keep in that way!",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(OrangeFD)
                        .clickable {
                            onPositiveClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Create New Habit Reminder",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(OrangeWhite)
                        .clickable {
                            onDismiss()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continue",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }


        }
    }

}

fun convertTo12HourFormat(time: String): String {
    // Define the input format
    val inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)
    // Define the output format
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

    // Parse the input time string
    val parsedTime = LocalTime.parse(time, inputFormatter)

    // Format the parsed time to the desired output format
    return parsedTime.format(outputFormatter)
}