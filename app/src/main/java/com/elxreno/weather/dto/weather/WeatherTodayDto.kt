package com.elxreno.weather.dto.weather

import com.elxreno.weather.dto.weather.today.*
import com.google.gson.annotations.SerializedName

data class WeatherTodayDto(

    @SerializedName("coord")
    val coordinates: TodayCoordinates,

    @SerializedName("weather")
    val description: ArrayList<TodayDescription>,

    @SerializedName("base")
    val base: String,

    @SerializedName("main")
    val main: TodayTemp,

    @SerializedName("wind")
    val wind: TodayWind,

    @SerializedName("clouds")
    val clouds: TodayClouds,

    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("sys")
    val sys: TodaySys,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val city: String,

    @SerializedName("cod")
    val code: Int

)