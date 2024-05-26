package com.example.habittracking.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.habittracking.domain.repository.HabitTrackingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HabitTrackingRepository,
    private val application: Application
) : ViewModel() {

}