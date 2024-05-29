package com.example.habittracking.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracking.common.Contacts
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.HabitEntry
import com.example.habittracking.presentation.ui.theme.Blue29
import com.example.habittracking.presentation.ui.theme.PurpleDark

@Composable
fun HabitCard(
    habit: Habit,
    habitColor: Color,
    habitEntries: List<HabitEntry>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = habit.habitName,
            fontFamily = Contacts.MANROPE_FONT_FAMILY,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = PurpleDark,
            modifier = Modifier.width(110.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))

        habitEntries.forEach { habitEntry ->
            if(habitEntry.status){
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(habitColor)
                )
            }
            else {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Transparent) // Ensure the background is transparent
                ) {
                    Canvas(modifier = Modifier.size(50.dp)) {
                        drawTriangle(habitColor)
                    }
                }
            }

            Spacer(modifier = Modifier.width(10.dp))
        }


    }
}

fun DrawScope.drawTriangle(color: Color) {
    val path = Path().apply {
        moveTo(size.width / 2, 0f) // Top center
        lineTo(size.width, size.height) // Bottom right
        lineTo(0f, size.height) // Bottom left
        close() // Close the path to form a triangle
    }

    drawPath(path, color = color)
}