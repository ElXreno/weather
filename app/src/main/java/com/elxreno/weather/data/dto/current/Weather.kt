package com.elxreno.weather.data.dto.current


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)