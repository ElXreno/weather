package com.elxreno.weather.dto


import com.elxreno.weather.dto.current.*
import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val rain: Rain,
    val snow: Snow,
    @SerializedName("dt")
    val timestamp: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    @SerializedName("name")
    val cityName: String,
    val cod: Int
)