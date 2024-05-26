package com.example.habittracking.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracking.R
import com.example.habittracking.common.Contacts.Companion.KLASIK_FONT_FAMILY

@Composable
fun NavigationView(
    modifier: Modifier,
    onHomeClick: () -> Unit,
    onAddClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingClick: () -> Unit,
    btnId: Int
) {
    val colorMatrix = ColorMatrix().apply {
        setToSaturation(0f)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //home
        Column(
            modifier = Modifier
                .width(80.dp)
                .clickable {
                    onHomeClick()
                }
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nav_home),
                contentDescription = "nav_icon",
                modifier = Modifier,
                colorFilter = if (btnId != 1) ColorFilter.colorMatrix(colorMatrix) else null
            )
        }

        //Add Habit
        Column(
            modifier = Modifier
                .width(80.dp)
                .clickable {
                    onAddClick()
                }
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nav_add),
                contentDescription = "nav_icon",
                modifier = Modifier,
                colorFilter = if (btnId != 2) ColorFilter.colorMatrix(colorMatrix) else null
            )
        }

        //profile
        Column(
            modifier = Modifier
                .width(80.dp)
                .clickable {
                    onProfileClick()
                }
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nav_profile),
                contentDescription = "nav_icon",
                modifier = Modifier,
                colorFilter = if (btnId != 3) ColorFilter.colorMatrix(colorMatrix) else null
            )
        }

        //settings
        Column(
            modifier = Modifier
                .width(80.dp)
                .clickable {
                    onSettingClick()
                }
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nav_setting),
                contentDescription = "nav_icon",
                modifier = Modifier,
                colorFilter = if (btnId != 4) ColorFilter.colorMatrix(colorMatrix) else null
            )
        }


    }
}