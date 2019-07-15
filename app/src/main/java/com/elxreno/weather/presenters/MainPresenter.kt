package com.elxreno.weather.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.BuildConfig
import com.elxreno.weather.models.weather.today.WeatherToday
import com.elxreno.weather.services.WeatherService
import com.elxreno.weather.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun onCreateSuccess() {
        val cityId = 620127
        val authToken = BuildConfig.OWM_TOKEN

        WeatherService().getInstance()
            .getWeatherApi()
            .getWeatherTodayById(cityId, authToken)
            .enqueue(object : Callback<WeatherToday> {
                override fun onFailure(call: Call<WeatherToday>, t: Throwable) {
                    viewState.showTodayWeather(t.message!!)
                }

                override fun onResponse(call: Call<WeatherToday>, response: Response<WeatherToday>) {
                    if (response.code() == 200) {
                        val weatherToday = response.body()

                        val result = "City: ${weatherToday?.city}\n" +
                                "Temperature: ${weatherToday?.temp?.temp}\n" +
                                "Temperature(Min): ${weatherToday?.temp?.minTemp}\n" +
                                "Temperature(Max): ${weatherToday?.temp?.maxTemp}\n"

                        viewState.showTodayWeather(result)
                    }

                }

            })
    }
}