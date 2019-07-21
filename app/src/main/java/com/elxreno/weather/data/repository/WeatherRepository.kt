package com.elxreno.weather.data.repository

import androidx.lifecycle.LiveData
import com.elxreno.weather.data.db.entities.CurrentWeatherEntity

interface WeatherRepository {
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>
}