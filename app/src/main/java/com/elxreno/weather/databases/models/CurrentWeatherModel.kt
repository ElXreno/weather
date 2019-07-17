package com.elxreno.weather.databases.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherModel(
    @PrimaryKey
    val id: Int,
    val city: String,
    val temp: Double
)