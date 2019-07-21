package com.elxreno.weather.data.repository

import androidx.lifecycle.LiveData
import com.elxreno.weather.data.db.entities.CurrentWeatherEntity

class WeatherRepositoryImpl : WeatherRepository {
    override fun getCurrentWeather(): LiveData<CurrentWeatherEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}