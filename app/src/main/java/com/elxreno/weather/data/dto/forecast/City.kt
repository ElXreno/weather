package com.elxreno.weather.data.dto.forecast


import androidx.room.Embedded
import com.elxreno.weather.data.dto.current.Coordinates
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    @Embedded(prefix = "coordinates_")
    val coordinates: Coordinates,
    val country: String,
    val id: Int,
    val name: String,
    val timezone: Int
)