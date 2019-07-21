package com.elxreno.weather.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    val city: String,
    val temp: Double,
    val windSpeed: Double,
    @PrimaryKey
    val id: Int = 0
)