package com.elxreno.weather.api

import com.elxreno.weather.dto.CurrentWeatherDto
import com.elxreno.weather.dto.WeatherForecastDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherApi {

    @GET("weather")
    fun getWeatherTodayById(
        @Query("id") id: Int,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Observable<CurrentWeatherDto>

    @GET("weather")
    fun getWeatherTodayByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Observable<CurrentWeatherDto>

    @GET("forecast")
    fun getWeatherForecastById(
        @Query("id") id: Int,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Observable<WeatherForecastDto>

    @GET("forecast")
    fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") token: String,
        @Query("units") units: String = "metric"
    ): Observable<WeatherForecastDto>
}