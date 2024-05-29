package com.example.habittracking.domain.model

import com.google.gson.annotations.SerializedName

data class Habit(
    val id: Int,
    @SerializedName("habit_name")
    val habitName: String,
    val frequency: String,
    @SerializedName("reminder_time")
    val reminderTime: String,
    val notification: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    val user: Int
)

/*
"id": 1,
"habit_name": "Read a book",
"frequency": "Daily",
"reminder_time": "09:00:00",
"notification": true,
"created_at": "2024-05-27T17:33:40.408898Z",
"user": 2*/
