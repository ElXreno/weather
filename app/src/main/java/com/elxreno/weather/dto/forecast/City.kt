package com.elxreno.weather.dto.forecast


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coordinates: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val timezone: Int
)