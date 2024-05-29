package com.example.habittracking.domain.model

import com.google.gson.annotations.SerializedName

data class HabitRequest(
    @SerializedName("habit_name")
    val habitName: String,
    val frequency: String,
    @SerializedName("reminder_time")
    val reminderTime: String,
    val notification: Boolean,
)
