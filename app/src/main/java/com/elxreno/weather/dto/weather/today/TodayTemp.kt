package com.elxreno.weather.dto.weather.today

import com.google.gson.annotations.SerializedName

data class TodayTemp (

    val temp: Double,

    @SerializedName("temp_min")
    val minTemp: Double,

    @SerializedName("temp_max")
    val maxTemp: Double

)