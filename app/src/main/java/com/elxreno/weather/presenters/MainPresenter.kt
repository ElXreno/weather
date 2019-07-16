package com.elxreno.weather.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.BuildConfig
import com.elxreno.weather.dto.weather.WeatherForecastDto
import com.elxreno.weather.dto.weather.WeatherTodayDto
import com.elxreno.weather.services.WeatherService
import com.elxreno.weather.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.util.*


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    // TODO: Move all business logic to models

    fun onCreateSuccess() {
        val cityId = 620127
        val authToken = BuildConfig.OWM_TOKEN

        WeatherService().getInstance()
            .getWeatherApi()
            .getWeatherTodayById(cityId, authToken)
            .enqueue(object : Callback<WeatherTodayDto> {
                override fun onFailure(call: Call<WeatherTodayDto>, t: Throwable) {
                    viewState.showTodayWeather(t.message!!)
                }

                override fun onResponse(call: Call<WeatherTodayDto>, response: Response<WeatherTodayDto>) {
                    if (response.code() == 200) {
                        val weatherToday = response.body()

                        val result = "City: ${weatherToday?.city}\n" +
                                "Temperature: ${weatherToday?.main?.temp} °C\n"

                        viewState.showTodayWeather(result)
                    }
                }

            })


        WeatherService().getInstance()
            .getWeatherApi()
            .getWeatherForecastById(cityId, authToken)
            .enqueue(object : Callback<WeatherForecastDto> {
                override fun onFailure(call: Call<WeatherForecastDto>, t: Throwable) {
                    viewState.showForecastWeather(t.message!!)
                }

                override fun onResponse(call: Call<WeatherForecastDto>, response: Response<WeatherForecastDto>) {
                    if (response.code() == 200) {
                        val weatherForecast = response.body()

                        var result = ""

                        weatherForecast?.list?.forEach {
                            val date = getDate(it.timestamp)

                            // TODO: Fix hardcoded strings
                            result += "\nDate: $date\n" +
                                    "Minimal temperature: ${it.main.minTemp} °C\n" +
                                    "Maximum temperature: ${it.main.maxTemp} °C\n" +
                                    "Wind speed: ${it.wind.speed} m/s\n"
                        }

                        viewState.showForecastWeather(result)

                    }
                }

            })
    }

    fun getDate(timeStamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(Date(timeStamp * 1000L))
    }
}