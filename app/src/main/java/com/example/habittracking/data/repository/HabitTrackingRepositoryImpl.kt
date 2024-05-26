package com.example.habittracking.data.repository

import com.example.habittracking.data.remote.HabitTrackingApiService
import com.example.habittracking.domain.repository.HabitTrackingRepository

class HabitTrackingRepositoryImpl(
    private val api: HabitTrackingApiService
) : HabitTrackingRepository {

}