package com.elxreno.weather.data.dto


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elxreno.weather.data.dto.forecast.City
import com.elxreno.weather.data.dto.forecast.Item

const val FORECAST_WEATHER_ID = 0

@Entity(tableName = "forecast_weather")
data class ForecastWeatherDto(
    val cod: String,
    val message: Double,
    @Embedded(prefix = "city_") val city: City,
    val cnt: Int,
    val list: List<Item>
) {
    @PrimaryKey(autoGenerate = false)
    var _id: Int = FORECAST_WEATHER_ID
}