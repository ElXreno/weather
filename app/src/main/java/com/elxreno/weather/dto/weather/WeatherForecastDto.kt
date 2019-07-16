package com.elxreno.weather.dto.weather

import com.elxreno.weather.dto.weather.forecast.ForecastCity
import com.elxreno.weather.dto.weather.forecast.ForecastItem

data class WeatherForecastDto(

    val cod: String,
    val message: Double,
    val cnt: Int,

    val list: ArrayList<ForecastItem>,

    val city: ForecastCity

)