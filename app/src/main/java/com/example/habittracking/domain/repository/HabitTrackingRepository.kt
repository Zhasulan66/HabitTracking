package com.example.habittracking.domain.repository

import com.example.habittracking.domain.model.Analytics
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.HabitEntry
import com.example.habittracking.domain.model.HabitEntryRequest
import com.example.habittracking.domain.model.HabitRequest
import com.example.habittracking.domain.model.auth.*

interface HabitTrackingRepository {

    //auth
    suspend fun registerUser(userRequest: UserRequest): UserResponse

    suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse

    suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    suspend fun loginUser(userLogin: UserLogin): TokenResponse

    suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    //habit
    suspend fun getAllHabits(): List<Habit>

    suspend fun createHabit(token: String, habitRequest: HabitRequest): Habit

    suspend fun updateHabit(id: Int, token: String, habitRequest: HabitRequest): Habit

    suspend fun getHabitById(id: Int): Habit

    suspend fun getAnalytics(id: Int): Analytics

    suspend fun getAllHabitEntries(): List<HabitEntry>

    suspend fun createHabitEntry(token: String, habitEntryRequest: HabitEntryRequest): HabitEntry

    suspend fun updateHabitEntry(id: Int, token: String, habitEntryRequest: HabitEntryRequest): HabitEntry

    suspend fun getUserByToken(token: String): UserResponse
}