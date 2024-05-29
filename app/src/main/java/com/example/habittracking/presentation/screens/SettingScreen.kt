package com.example.habittracking.presentation.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.habittracking.R
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.LanguageManager
import com.example.habittracking.presentation.navigation.NavigationView
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark
import com.example.habittracking.presentation.viewmodel.MainViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    languageManager: LanguageManager
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val context = LocalContext.current
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
                        painter = painterResource(R.drawable.ic_hamburger_menu),
                        contentDescription = "icon"
                    )
                }

                //screen title
                Text(
                    text = stringResource(id = R.string.settings),
                    fontFamily = MANROPE_FONT_FAMILY,
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
            ) {
                Image(
                    painter = painterResource(R.drawable.setting_card_img),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.FillHeight
                )

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.check_your_profile),
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                    Text(
                        text = "youremail@gmail.com",
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(OrangeFD)
                            .clickable {
                                navController.navigate(Screen.ProfileScreen.route) {
                                    popUpTo(Screen.SettingScreen.route) {
                                        inclusive = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.view),
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PurpleDark
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.general),
                fontFamily = MANROPE_FONT_FAMILY,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = PurpleDark
            )
            Spacer(modifier = Modifier.height(10.dp))

            //notification
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_notification),
                            contentDescription = "img"
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = stringResource(id = R.string.notification),
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = PurpleDark
                        )
                        Text(
                            text = stringResource(id = R.string.customize_notification),
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "icon",
                    tint = PurpleDark,
                    modifier = Modifier.clickable { }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            //language
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_more),
                            contentDescription = "img"
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = stringResource(id = R.string.language),
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = PurpleDark
                        )
                        Text(
                            text = stringResource(id = R.string.switch_language),
                            fontFamily = MANROPE_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .clickable {
                            if (languageManager.getCurrentLanguage() == "ru") {
                                languageManager.setLanguage("en")
                                languageManager.restartActivity()
                            } else {
                                languageManager.setLanguage("ru")
                                languageManager.restartActivity()
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (languageManager.getCurrentLanguage() == "ru") {
                            "RU"
                        } else {
                            "EN"
                        },
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = PurpleDark
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "icon",
                        tint = PurpleDark
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.support),
                fontFamily = MANROPE_FONT_FAMILY,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = PurpleDark
            )
            Spacer(modifier = Modifier.height(10.dp))

            //contact
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_contact),
                            contentDescription = "img"
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.contact),
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
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("habit0support@gmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "Habit App Support")
                        }
                        context.startActivity(intent)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            //feedback
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_feedback),
                            contentDescription = "img"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.feedback),
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
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/"))
                        context.startActivity(intent)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            //privacy policy
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_privacy),
                            contentDescription = "img"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.privacy_policy),
                        fontFamily = MANROPE_FONT_FAMILY,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = PurpleDark,
                        modifier = Modifier.fillMaxWidth(0.8f)
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

            //exit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(
                                    OrangeFD.red,
                                    OrangeFD.green,
                                    OrangeFD.blue,
                                    alpha = 0.1f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_about),
                            contentDescription = "img"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.exit),
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
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.SettingScreen.route) {
                                inclusive = true
                            }
                        }
                        viewModel.deleteToken()
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))


        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.SettingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onAddClick = {
                navController.navigate(Screen.NewHabitScreen.route) {
                    popUpTo(Screen.SettingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onProfileClick = {
                navController.navigate(Screen.ProfileScreen.route) {
                    popUpTo(Screen.SettingScreen.route) {
                        inclusive = true
                    }
                }
            },
            onSettingClick = {
                navController.navigate(Screen.SettingScreen.route) {
                    popUpTo(Screen.SettingScreen.route) {
                        inclusive = true
                    }
                }
            },
            btnId = 4,
        )
    }
}