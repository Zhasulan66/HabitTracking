package com.example.habittracking.presentation.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.habittracking.presentation.components.HabitCard
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.Purple97
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.ui.theme.RedF6
import com.example.habittracking.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val habitListState by viewModel.habitListState.collectAsState()
    val habitEntriesState by viewModel.habitEntriesState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ) {
        Image(
            painter = painterResource(R.drawable.home_bg),
            contentDescription = "img",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )

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
            ){
                //menu
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
                        painter = painterResource(R.drawable.ic_hamburger_menu),
                        contentDescription = "icon"
                    )
                }

                //screen title
                Text(
                    text = stringResource(id = R.string.homepage),
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
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(146.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
            ) {
                Image(
                    painter = painterResource(R.drawable.home_card_img),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.FillHeight
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.first_our_habit),
                        fontFamily = KLASIK_FONT_FAMILY,
                        fontSize = 18.sp,
                        color = PurpleDark
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "- ANONYMOUS",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            when (habitListState) {
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
                    when (habitEntriesState) {
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
                            val habitEntries = (habitEntriesState as Resource.Success<List<HabitEntry>>).data
                            val habitList = (habitListState as Resource.Success<List<Habit>>).data
                            HabitListScreen(navController, habitList, habitEntries)
                        }

                        else -> {}
                    }
                }
                else -> {
                    viewModel.fetchAllHabitEntries()
                    viewModel.fetchAllHabit()
                }
            }

        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            },
            onAddClick = {
                navController.navigate(Screen.NewHabitScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            },
            onSettingClick = {
                navController.navigate(Screen.SettingScreen.route) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 1,
        )
    }

}

@Composable
fun HabitListScreen(
    navController: NavController,
    habitList: List<Habit>,
    habitEntries: List<HabitEntry>
){
    val inputDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val dayOfMonthFormatter = SimpleDateFormat("d", Locale.ENGLISH)
    val dayOfWeekFormatter = SimpleDateFormat("EEE", Locale.ENGLISH)

    // Convert the list of HabitEntry to a set of parsed Dates to remove duplicates
    val uniqueDates = habitEntries.map { inputDateFormatter.parse(it.date) }.toSet()
    val sortedDates = uniqueDates.sorted()

    val sortedDatesWithWeekdays = sortedDates.map { date ->
        val dayOfMonth = dayOfMonthFormatter.format(date)
        val dayOfWeek = dayOfWeekFormatter.format(date).uppercase(Locale.ENGLISH)
        dayOfMonth to dayOfWeek
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                //habit days
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.habits),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MANROPE_FONT_FAMILY,
                        color = PurpleDark,
                        modifier = Modifier.width(110.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    sortedDatesWithWeekdays.forEach { dateAndWeek ->
                        Column(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = dateAndWeek.second,
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                            Text(
                                text = dateAndWeek.first,
                                fontFamily = MANROPE_FONT_FAMILY,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PurpleDark
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))

                val colors = listOf(OrangeFC, RedF6, Blue29, Purple97)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(habitList){ habit ->
                        val myHabitEntries = habitEntries.filter { it.habit == habit.id }.sortedBy { it.date }
                        val color = colors[habitList.indexOf(habit) % colors.size]
                        HabitCard(habit, color, myHabitEntries){
                            navController.navigate(Screen.HabitInfoScreen.route + "/${habit.id}")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }




}