package com.elxreno.weather.data.dto


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elxreno.weather.data.dto.current.*
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherDto(
    @Embedded(prefix = "coordinates_") @SerializedName("coord") val coordinates: Coordinates,
    val weather: List<Weather>,
    val base: String,
    @Embedded(prefix = "main_") val main: Main,
    val visibility: Int,
    @Embedded(prefix = "wind_") val wind: Wind,
    @Embedded(prefix = "clouds_") val clouds: Clouds,
    @Embedded(prefix = "rain_") val rain: Rain?,
    @Embedded(prefix = "snow_") val snow: Snow?,
    val timestamp: Long,
    @Embedded(prefix = "sys_") val sys: Sys,
    val timezone: Int,
    @SerializedName("name") val cityName: String,
    val cod: Int
) {
    @PrimaryKey(autoGenerate = false)
    var _id: Int = CURRENT_WEATHER_ID
}