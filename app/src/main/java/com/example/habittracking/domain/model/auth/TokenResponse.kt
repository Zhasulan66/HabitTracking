package com.example.habittracking.domain.model.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("auth_token")
    var authToken: String
)
