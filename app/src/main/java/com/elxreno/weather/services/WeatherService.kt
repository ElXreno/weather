package com.elxreno.weather.services

import com.elxreno.weather.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    private var mRetrofit: Retrofit
    private lateinit var mInstance: WeatherService
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

        mRetrofit = Retrofit.Builder()
            .client(client)
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