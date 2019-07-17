package com.elxreno.weather.dto.current


import com.google.gson.annotations.SerializedName

data class Main(
    val humidity: Double,
    val pressure: Double,
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)