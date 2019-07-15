package com.elxreno.weather.models.weather.today

import com.elxreno.weather.models.weather.*
import com.google.gson.annotations.SerializedName

data class WeatherToday(

    @SerializedName("coord")
    val coordinates: WeatherCoordinates,

    @SerializedName("weather")
    val description: ArrayList<WeatherDescription>,

    @SerializedName("base")
    val base: String,

    @SerializedName("main")
    val temp: WeatherTemp,

    @SerializedName("wind")
    val wind: WeatherWind,

    @SerializedName("clouds")
    val clouds: WeatherClouds,

    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("sys")
    val sys: WeatherSys,

    @SerializedName("timezone")
    val timezone: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val city: String,

    @SerializedName("cod")
    val code: Int

)