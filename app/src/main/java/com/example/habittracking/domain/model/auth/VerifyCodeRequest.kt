package com.example.habittracking.domain.model.auth

data class VerifyCodeRequest(
    val code: String,
    val email: String
)
