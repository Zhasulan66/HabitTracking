package com.example.habittracking.domain.model

data class HabitEntryUpdate(
    val habit: Int,
    val date: String,
    val status: Boolean,
)
