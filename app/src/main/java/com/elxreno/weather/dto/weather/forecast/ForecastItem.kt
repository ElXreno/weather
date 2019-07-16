package com.elxreno.weather.dto.weather.forecast

import com.elxreno.weather.dto.weather.today.TodayClouds
import com.elxreno.weather.dto.weather.today.TodayWind
import com.google.gson.annotations.SerializedName

data class ForecastItem(

    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("dt_txt")
    val timestampText: String,

    val main: ForecastMain,
    val weather: ArrayList<ForecastWeather>,
    val clouds: TodayClouds,
    val wind: TodayWind,
    val sys: ForecastSys

)