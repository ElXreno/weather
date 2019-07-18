package com.elxreno.weather.di.modules

import android.content.Context
import androidx.room.Room
import com.elxreno.weather.dao.CurrentWeatherDao
import com.elxreno.weather.databases.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather")
            .build()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(weatherDatabase: WeatherDatabase): CurrentWeatherDao =
        weatherDatabase.currentWeatherDao()
}