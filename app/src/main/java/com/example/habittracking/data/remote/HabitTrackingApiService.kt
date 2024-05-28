package com.example.habittracking.data.remote

import com.example.habittracking.domain.model.auth.*
import retrofit2.http.Body
import retrofit2.http.POST

interface HabitTrackingApiService {

    @POST("/register/")
    suspend fun registerUser(@Body userRequest: UserRequest): UserResponse

    @POST("/verify_email/")
    suspend fun verifyEmail(@Body verificationRequest: VerificationRequest): MessageResponse

    @POST("/confirm_email/")
    suspend fun confirmEmailCode(@Body confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    @POST("/login/")
    suspend fun loginUser(@Body userLogin: UserLogin): TokenResponse

    @POST("/send_reset_code/")
    suspend fun sendResetCode(@Body resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    @POST("/verify_reset_code/")
    suspend fun verifyResetCode(@Body verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    @POST("/reset_password/")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse
}