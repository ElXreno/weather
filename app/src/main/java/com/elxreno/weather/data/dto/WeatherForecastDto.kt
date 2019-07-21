package com.elxreno.weather.data.dto


import com.elxreno.weather.data.dto.forecast.City
import com.elxreno.weather.data.dto.forecast.Item

data class WeatherForecastDto(
    val cod: String,
    val message: Double,
    val city: City,
    val cnt: Int,
    val list: List<Item>
)