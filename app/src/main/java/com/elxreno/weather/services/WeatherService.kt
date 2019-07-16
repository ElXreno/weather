package com.elxreno.weather.services

import com.elxreno.weather.api.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    private var mRetrofit: Retrofit
    private lateinit var mInstance: WeatherService
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): WeatherService {
        if (!::mInstance.isInitialized) mInstance = WeatherService()

        return mInstance
    }

    fun getWeatherApi(): WeatherApi = mRetrofit.create(WeatherApi::class.java)

}