package com.example.habittracking.domain.model.auth

data class ConfirmCodeRequest(
    val email: String,
    val code: String,
)
