package com.example.habittracking.presentation.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracking.common.Contacts
import com.example.habittracking.common.Contacts.Companion.MANROPE_FONT_FAMILY
import com.example.habittracking.presentation.navigation.Screen
import com.example.habittracking.presentation.ui.theme.GrayC8
import com.example.habittracking.presentation.ui.theme.OrangeFC
import com.example.habittracking.presentation.ui.theme.OrangeFD
import com.example.habittracking.presentation.ui.theme.OrangeWhite
import com.example.habittracking.presentation.ui.theme.PurpleDark

@Composable
fun ChooseReminderDialog(
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit,
    modifier: Modifier = Modifier,
    reminderList: MutableList<String>
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
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .then(modifier)
            .background(Color.White)
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                item{
                    val chunkedReminder = reminderList.chunked(3)

                    for(reminder in chunkedReminder){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            reminder.forEach { reminderText ->
                                var isChecked by remember { mutableStateOf(false) }
                                Column(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(if (isChecked) OrangeWhite else GrayC8)
                                        .padding(10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = reminderText,
                                        fontFamily = MANROPE_FONT_FAMILY,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isChecked) OrangeFD else PurpleDark
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
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

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
                            text = "Add Reminder",
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
}

@Composable
fun AddReminderDialog(
    onDismiss: () -> Unit,
    onPositiveClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTime = remember { mutableStateOf("00:00") }
    var isAm by remember { mutableStateOf(true) }

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
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .then(modifier)
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Cancel",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangeFC,
                    modifier = Modifier
                        .clickable {
                            onDismiss()
                        }
                )
                Text(
                    text = "Add Reminder",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )
                Text(
                    text = "Save",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangeFC,
                    modifier = Modifier
                        .clickable {
                            if(selectedTime.value != "00:00"){
                                onPositiveClick(selectedTime.value + if(isAm) "AM" else "PM")
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))


            TimePicker(selectedTime = selectedTime)
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(if (isAm) OrangeFD else OrangeWhite)
                    .clickable {
                        isAm = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "am",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isAm) PurpleDark else OrangeFD
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(if (!isAm) OrangeFD else OrangeWhite)
                    .clickable {
                        isAm = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "pm",
                    fontFamily = MANROPE_FONT_FAMILY,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (!isAm) PurpleDark else OrangeFD
                )
            }
        }
    }
}


@Composable
fun TimePicker(selectedTime: MutableState<String>) {
    val hours = (0..12).toList()
    val minutes = (0..59).toList()

    val hourState = rememberLazyListState()
    val minuteState = rememberLazyListState()

    // Update selected time when the selected item changes
    LaunchedEffect(hourState.firstVisibleItemScrollOffset, minuteState.firstVisibleItemScrollOffset) {
        val hourIndex = getCenteredIndex(hourState, hours.size)
        val minuteIndex = getCenteredIndex(minuteState, minutes.size)
        selectedTime.value = String.format("%02d:%02d", hourIndex, minuteIndex)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TimeScroller(state = hourState, items = hours)
        Text(text = ":", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        TimeScroller(state = minuteState, items = minutes)
    }
}

@Composable
fun TimeScroller(
    state: LazyListState,
    items: List<Int>
) {
    val visibleItems = 5 // Total items to be visible in the view, including selected item

    LazyColumn(
        state = state,
        modifier = Modifier
            .width(60.dp)
            .height(200.dp), // Adjust height to show 5 items
        verticalArrangement = Arrangement.spacedBy(4.dp), // Add spacing between items
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items.size) { index ->
            val centerIndex = (visibleItems - 1) / 2
            val isSelected = index == getCenteredIndex(state, items.size)
            val textSize = if (isSelected) 40.sp else 32.sp
            val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

            Text(
                text = String.format("%02d", items[index]),
                fontSize = textSize,
                fontWeight = fontWeight,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

fun getCenteredIndex(state: LazyListState, itemCount: Int): Int {
    val centerIndex = state.firstVisibleItemIndex + (state.layoutInfo.visibleItemsInfo.size / 2)
    return centerIndex.coerceIn(0, itemCount - 1)
}