package com.example.habittracking.domain.model.auth

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    val code: String,
    val email: String,
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("confirm_password")
    val confirmPassword: String
)
