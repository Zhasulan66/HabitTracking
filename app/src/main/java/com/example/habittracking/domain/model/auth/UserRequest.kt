package com.example.habittracking.domain.model.auth

data class UserRequest(
    val username: String,
    val email: String,
    val password: String,
)
