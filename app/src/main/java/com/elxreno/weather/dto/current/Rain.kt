package com.elxreno.weather.dto.current

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h1: Double,
    @SerializedName("3h")
    val h3: Double
)