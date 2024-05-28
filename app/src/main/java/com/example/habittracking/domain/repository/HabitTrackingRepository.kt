package com.example.habittracking.domain.repository

import com.example.habittracking.domain.model.auth.*

interface HabitTrackingRepository {

    suspend fun registerUser(userRequest: UserRequest): UserResponse

    suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse

    suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    suspend fun loginUser(userLogin: UserLogin): TokenResponse

    suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse
}