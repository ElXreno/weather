package com.elxreno.weather.dto.forecast

import com.google.gson.annotations.SerializedName

data class Snow(@SerializedName("3h") val h3: Double)