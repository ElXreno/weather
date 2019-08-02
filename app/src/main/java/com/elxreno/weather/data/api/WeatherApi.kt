package com.elxreno.weather.data.api

import com.elxreno.weather.data.dto.CurrentWeatherDto
import com.elxreno.weather.data.dto.ForecastWeatherDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherTodayById(
        @Query("id") id: Int,
        @Query("units") units: String = "metric"
    ): Observable<CurrentWeatherDto>

    @GET("weather")
    fun getWeatherTodayByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric"
    ): Observable<CurrentWeatherDto>

    @GET("forecast")
    fun getWeatherForecastById(
        @Query("id") id: Int,
        @Query("units") units: String = "metric"
    ): Observable<ForecastWeatherDto>

    @GET("forecast")
    fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric"
    ): Observable<ForecastWeatherDto>
}