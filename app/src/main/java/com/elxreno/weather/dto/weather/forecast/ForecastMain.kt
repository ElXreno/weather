package com.elxreno.weather.dto.weather.forecast

import com.google.gson.annotations.SerializedName

data class ForecastMain(

    val temp: Double,

    @SerializedName("temp_min")
    val minTemp: Double,

    @SerializedName("temp_max")
    val maxTemp: Double,

    val pressure: Double,

    @SerializedName("sea_level")
    val seaLevel: Double,

    @SerializedName("grnd_level")
    val groundLevel: Double,

    val humidity: Int,

    @SerializedName("temp_kf")
    val kfTemp: Double

)