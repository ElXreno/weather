package com.elxreno.weather.dto.forecast


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("grnd_level")
    val grndLevel: Double,
    val humidity: Double,
    val pressure: Double,
    @SerializedName("sea_level")
    val seaLevel: Double,
    val temp: Double,
    @SerializedName("temp_kf")
    val kfTemp: Double,
    @SerializedName("temp_max")
    val maxTemp: Double,
    @SerializedName("temp_min")
    val minTemp: Double
)