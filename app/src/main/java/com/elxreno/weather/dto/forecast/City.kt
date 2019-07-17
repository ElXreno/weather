package com.elxreno.weather.dto.forecast


import com.elxreno.weather.dto.current.Coordinates
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val country: String,
    val id: Int,
    val name: String,
    val timezone: Int
)