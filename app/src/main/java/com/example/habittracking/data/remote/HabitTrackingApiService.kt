package com.example.habittracking.data.remote

import com.example.habittracking.domain.model.Analytics
import com.example.habittracking.domain.model.Habit
import com.example.habittracking.domain.model.HabitEntry
import com.example.habittracking.domain.model.HabitEntryRequest
import com.example.habittracking.domain.model.HabitRequest
import com.example.habittracking.domain.model.auth.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HabitTrackingApiService {

    //auth
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

    //habit
    @GET("/api/habits/")
    suspend fun getAllHabits(): List<Habit>

    @POST("/api/habits/")
    suspend fun createHabit(
        @Header("Authorization") token: String,
        @Body habitRequest: HabitRequest
    ): Habit

    @PUT("/api/habits/{id}/")
    suspend fun updateHabit(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Body habitRequest: HabitRequest
    ): Habit

    @GET("/api/habits/{id}/")
    suspend fun getHabitById(
        @Path("id") id: Int
    ): Habit

    @GET("/api/analytics/{id}/")
    suspend fun getAnalyticsById(
        @Path("id") id: Int
    ): Analytics

    @GET("/api/habit-entries/")
    suspend fun getAllHabitEntries(): List<HabitEntry>

    @POST("/api/habit-entries/")
    suspend fun createHabitEntry(
        @Header("Authorization") token: String,
        @Body habitEntryRequest: HabitEntryRequest
    ): HabitEntry

    @PUT("/api/habit-entries/{id}")
    suspend fun updateHabitEntry(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Body habitEntryRequest: HabitEntryRequest
    ): HabitEntry

    @GET("/users/me/")
    suspend fun getUserByToken(
        @Header("Authorization") token: String,
    ): UserResponse


}