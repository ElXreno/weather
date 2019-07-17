package com.elxreno.weather.dto.forecast


import com.google.gson.annotations.SerializedName

data class Rain(@SerializedName("3h") val h3: Double)