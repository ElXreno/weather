package com.elxreno.weather.api

import com.elxreno.weather.dto.weather.WeatherForecastDto
import com.elxreno.weather.dto.weather.WeatherTodayDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getWeatherTodayById(
        @Query("id") id: Int,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherTodayDto>

    @GET("weather")
    fun getWeatherTodayByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherTodayDto>

    @GET("forecast")
    fun getWeatherForecastById(
        @Query("id") id: Int,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherForecastDto>

    @GET("forecast")
    fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherForecastDto>
}