package com.example.habittracking.domain.model

import com.google.gson.annotations.SerializedName

data class Analytics(
    val id: Int,
    @SerializedName("completed_count")
    val completedCount: Int,
    @SerializedName("missed_count")
    val missedCount: Int,
    val streak: Int,
    @SerializedName("longest_streak")
    val longestStreak: Int,
    @SerializedName("completion_rate")
    val completionRate: Float,
    @SerializedName("average_easiness_score")
    val averageEasinessScore: Float,
    val habit: Int
)

/*
"id": 1,
"completed_count": 4,
"missed_count": 0,
"streak": 1,
"longest_streak": 1,
"completion_rate": 100.0,
"average_easiness_score": 0.0,
"habit": 1*/
