package com.example.habittracking.domain.model

data class HabitEntry(
    val id: Int,
    val date: String,
    val status: Boolean,
    val note: String,
    val habit: Int
)

/*"id": 4,
"date": "2024-05-27",
"status": true,
"note": "Read 20 pages",
"habit": 1*/
