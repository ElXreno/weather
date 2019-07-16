package com.elxreno.weather.dto.weather.forecast

import com.elxreno.weather.dto.weather.today.TodayCoordinates
import com.google.gson.annotations.SerializedName

data class ForecastCity(

    val id: Int,
    val name: String,
    val country: String,
    val timezone: Int,

    @SerializedName("coord")
    val coordinates: TodayCoordinates

)