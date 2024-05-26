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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<MainViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeWhite)
    ) {
        Image(
            painter = painterResource(R.drawable.home_bg),
            contentDescription = "img",
            modifier = Modifier.fillMaxWidth()
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
                        .background(Color(PurpleDark.red, PurpleDark.green, PurpleDark.blue, alpha = 0.1f))
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
                    text = "Homepage",
                    fontFamily = Contacts.MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(PurpleDark.red, PurpleDark.green, PurpleDark.blue, alpha = 0.1f))
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
                    modifier = Modifier.fillMaxHeight()
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.FillHeight
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                ){
                    Text(
                        text = "We first make our habits, and then our habits makes us",
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "HABITS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MANROPE_FONT_FAMILY,
                    color = PurpleDark
                )
                Spacer(modifier = Modifier.width(40.dp))

                Column(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "SUN",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = "17",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "MON",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = "18",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Read A Book",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark,
                    modifier = Modifier.fillMaxWidth(0.4f)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(OrangeFC)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(OrangeFC)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Wake Up Early",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark,
                    modifier = Modifier.fillMaxWidth(0.4f)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Blue29)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Blue29)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Blue29)
                )
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