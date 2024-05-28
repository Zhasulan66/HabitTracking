package com.example.habittracking.domain.model.auth

data class VerifyCodeResponse(
    val message: String?,
    val email: String?,
    val detail: String?
)
