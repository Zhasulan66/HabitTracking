package com.example.habittracking.di

import com.example.habittracking.data.remote.HabitTrackingApiService
import com.example.habittracking.data.repository.HabitTrackingRepositoryImpl
import com.example.habittracking.domain.repository.HabitTrackingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HabitTrackingModule {

    @Provides
    @Singleton
    fun provideHabitTrackingApiService(): HabitTrackingApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://some.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HabitTrackingApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideReserveEasyRepository(
        habitTrackingApiService: HabitTrackingApiService
    ) : HabitTrackingRepository {
        return HabitTrackingRepositoryImpl(habitTrackingApiService)
    }

}