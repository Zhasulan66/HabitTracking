package com.example.habittracking.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.HabitEntryRequest
import com.example.habittracking.domain.model.HabitRequest
import com.example.habittracking.domain.model.Resource
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun UpdateHabitScreen(
    navController: NavController,
    habitId: Int,
    habitName: String,
    isHabitChecked: Boolean
){
    val viewModel = hiltViewModel<MainViewModel>()
    val updateHabitState by viewModel.updateHabitState.collectAsState()
    val savedToken: String? by viewModel.readToken().collectAsState(initial = null)

    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    when (updateHabitState) {
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
            val habit = (updateHabitState as Resource.Success<Habit>).data
            Toast.makeText(LocalContext.current, stringResource(id = R.string.habit_updated_successfully), Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.HomeScreen.route){
                popUpTo(Screen.NewHabitScreen.route){
                    inclusive = true
                }
            }
            viewModel.updateHabitSuccess()
        }
        is Resource.Initial -> {
            UpdateHabitFields(
                navController = navController,
                viewModel = viewModel,
                habitId = habitId,
                habitName = habitName,
                isHabitChecked = isHabitChecked
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateHabitFields(
    navController: NavController,
    viewModel: MainViewModel,
    habitId: Int,
    habitName: String,
    isHabitChecked: Boolean
) {
    var myHabitName by remember { mutableStateOf(habitName) }

    var showChooseReminderDialog by remember { mutableStateOf(false) }
    var showAddReminderDialog by remember { mutableStateOf(false) }

    var reminderTime by remember { mutableStateOf("10:00 AM") }
    var isChecked by remember { mutableStateOf(isHabitChecked) }

    val savedToken: String? by viewModel.readToken().collectAsState(initial = null)

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
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.NewHabitScreen.route) {
                                    inclusive = true
                                }
                            }
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
                    text = stringResource(id = R.string.new_habit),
                    fontFamily = Contacts.MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            //habit name
            TextField(
                value = myHabitName,
                onValueChange = { text ->
                    myHabitName = text
                },
                textStyle = TextStyle(
                    color = OrangeFD
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_habit_name),
                        fontSize = 14.sp,
                        fontFamily = Contacts.MANROPE_FONT_FAMILY,
                        color = Color.Gray,
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp)
            ){
                Text(
                    text = stringResource(id = R.string.habit_frequency),
                    fontFamily = Contacts.MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PurpleDark
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    //sun
                    Column{
                        Text(
                            text = stringResource(id = R.string.sun)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //mon
                    Column{
                        Text(
                            text = stringResource(id = R.string.mon)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //tue
                    Column{
                        Text(
                            text = stringResource(id = R.string.tue)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //wed
                    Column{
                        Text(
                            text = stringResource(id = R.string.wed)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //thu
                    Column{
                        Text(
                            text = stringResource(id = R.string.thu)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //fri
                    Column{
                        Text(
                            text = stringResource(id = R.string.fri)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    //sat
                    Column{
                        Text(
                            text = stringResource(id = R.string.sat)
                        )
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(OrangeFC)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(id = R.string.reminder),
                    fontFamily = Contacts.MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PurpleDark
                )
                Row(
                    modifier = Modifier
                        .clickable {
                            showChooseReminderDialog = true
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = reminderTime,
                        fontFamily = Contacts.MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = OrangeFD
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "icon",
                        tint = OrangeFD
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(id = R.string.notification),
                    fontFamily = Contacts.MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = PurpleDark
                )

                Switch(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    thumbContent = {
                        Box(
                            modifier = Modifier
                                .height(8.dp)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .background(if (isChecked) OrangeFD else PurpleDark)
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OrangeFD,
                        uncheckedThumbColor = PurpleDark,
                        checkedTrackColor = Color.LightGray,
                        uncheckedTrackColor = Color.LightGray,
                        checkedBorderColor = Color.LightGray,
                        uncheckedBorderColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .scale(0.8f)
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .offset(y = 40.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.start_this_habit),
                        fontFamily = Contacts.KLASIK_FONT_FAMILY,
                        fontSize = 20.sp,
                        color = PurpleDark
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.click_to_update),
                        fontFamily = Contacts.MANROPE_FONT_FAMILY,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_down),
                        contentDescription = "icon"
                    )
                }

                Image(
                    painter = painterResource(R.drawable.add_habit_person),
                    contentDescription = "img",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(70.dp)
                        .clip(CircleShape)

                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = "img",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable {
                        if(myHabitName.isNotEmpty()){
                            savedToken?.let {
                                viewModel.updateHabit(
                                    habitId,
                                    it,
                                    HabitRequest(
                                        habitName = myHabitName,
                                        frequency = "Daily",
                                        reminderTime = convertTo24HourFormat(reminderTime),
                                        notification = isChecked
                                    )
                                )
                            }
                        }

                    }
            )


        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.UpdateHabitScreen.route) {
                        inclusive = true
                    }
                }
            },
            onAddClick = {
                navController.navigate(Screen.NewHabitScreen.route) {
                    popUpTo(Screen.UpdateHabitScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.UpdateHabitScreen.route) {
                        inclusive = true
                    }
                }
            },
            onSettingClick = {
                navController.navigate(Screen.SettingScreen.route) {
                    popUpTo(Screen.UpdateHabitScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 2,
        )

        //choose reminder dialog
        if(showChooseReminderDialog){
            ChooseReminderDialog(
                onDismiss = { showChooseReminderDialog = false },
                onPositiveClick = {
                    showChooseReminderDialog = false
                    showAddReminderDialog = true
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                reminderTime = reminderTime
            )
        }

        //add reminder dialog
        if(showAddReminderDialog){
            AddReminderDialog(
                onDismiss = { showAddReminderDialog = false },
                onPositiveClick = { reminder ->
                    reminderTime = reminder
                    showAddReminderDialog = false
                    showChooseReminderDialog = true
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }

}