package com.elxreno.weather.models.weather

import com.google.gson.annotations.SerializedName

data class WeatherTemp (

    val temp: Double,

    @SerializedName("temp_min")
    val minTemp: Double,

    @SerializedName("temp_max")
    val maxTemp: Double

)