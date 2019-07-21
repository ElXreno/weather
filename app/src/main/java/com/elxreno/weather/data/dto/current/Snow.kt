package com.elxreno.weather.data.dto.current

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h")
    val h1: Double,
    @SerializedName("3h")
    val h3: Double
)