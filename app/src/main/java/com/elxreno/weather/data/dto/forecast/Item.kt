package com.elxreno.weather.data.dto.forecast


import com.google.gson.annotations.SerializedName

data class Item(
    val clouds: Clouds,
    @SerializedName("dt")
    val timestamp: Long,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val rain: Rain?,
    val snow: Snow?,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)