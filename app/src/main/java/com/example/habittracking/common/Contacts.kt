package com.example.habittracking.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.habittracking.R

class Contacts {

    companion object {
        val KLASIK_FONT_FAMILY = FontFamily(
            Font(R.font.klasik_regular),
            Font(R.font.klasik_regular, FontWeight.Bold),
            Font(R.font.klasik_regular, FontWeight.SemiBold),
            Font(R.font.klasik_regular, FontWeight.Medium),
            Font(R.font.klasik_regular, FontWeight.Light),
            Font(R.font.klasik_regular, FontWeight.ExtraLight),
        )

        val MANROPE_FONT_FAMILY = FontFamily(
            Font(R.font.manrope_regular),
            Font(R.font.manrope_bold, FontWeight.Bold),
            Font(R.font.manrope_semi_bold, FontWeight.SemiBold),
            Font(R.font.manrope_medium, FontWeight.Medium),
            Font(R.font.manrope_light, FontWeight.Light),
            Font(R.font.manrope_extra_light, FontWeight.ExtraLight),
        )
    }
}