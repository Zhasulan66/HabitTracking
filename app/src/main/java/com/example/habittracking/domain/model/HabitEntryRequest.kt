package com.example.habittracking.domain.model

data class HabitEntryRequest(
    val habit: Int,
    val date: String,
    val status: Boolean,
    val note: String,
)
