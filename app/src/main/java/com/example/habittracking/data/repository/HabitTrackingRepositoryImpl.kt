package com.example.habittracking.data.repository

import com.example.habittracking.data.remote.HabitTrackingApiService
import com.example.habittracking.domain.model.auth.*
import com.example.habittracking.domain.repository.HabitTrackingRepository

class HabitTrackingRepositoryImpl(
    private val api: HabitTrackingApiService
) : HabitTrackingRepository {

    override suspend fun registerUser(userRequest: UserRequest): UserResponse {
        return api.registerUser(userRequest)
    }

    override suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse {
        return api.verifyEmail(verificationRequest)
    }

    override suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse {
        return api.confirmEmailCode(confirmCodeRequest)
    }

    override suspend fun loginUser(userLogin: UserLogin): TokenResponse {
        return api.loginUser(userLogin)
    }

    override suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse {
        return api.sendResetCode(resetCodeRequest)
    }

    override suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse {
        return api.verifyResetCode(verifyCodeRequest)
    }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse {
        return api.resetPassword(resetPasswordRequest)
    }
}