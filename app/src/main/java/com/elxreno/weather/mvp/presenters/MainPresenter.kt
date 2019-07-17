package com.elxreno.weather.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.BuildConfig
import com.elxreno.weather.api.WeatherApi
import com.elxreno.weather.databases.WeatherDatabase
import com.elxreno.weather.databases.models.CurrentWeatherModel
import com.elxreno.weather.dto.WeatherCurrentDto
import com.elxreno.weather.dto.WeatherForecastDto
import com.elxreno.weather.mvp.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.util.*
import javax.inject.Inject


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var weatherApi: WeatherApi
    @Inject
    lateinit var weatherDatabase: WeatherDatabase

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val cityId = 620127
        val authToken = BuildConfig.openWeatherMapKey

        val currentWeather = weatherDatabase.currentWeatherDao().getOne()

        if (currentWeather != null) {
            Log.w("DEBUG", currentWeather.toString())
            viewState.showTodayWeather(
                "City: ${currentWeather.city}\n" +
                        "Temperature: ${currentWeather.temp} °C"
            )
        }

        weatherApi
            .getWeatherTodayById(cityId, authToken)
            .enqueue(object : Callback<WeatherCurrentDto> {
                override fun onFailure(call: Call<WeatherCurrentDto>, t: Throwable) {
                    viewState.showTodayWeather(t.message!!)
                }

                override fun onResponse(call: Call<WeatherCurrentDto>, response: Response<WeatherCurrentDto>) {
                    if (response.code() == 200) {
                        val weatherCurrent = response.body()

                        val result = "City: ${weatherCurrent?.cityName}\n" +
                                "Temperature: ${weatherCurrent?.main?.temp} °C"

                        viewState.showTodayWeather(result)

                        weatherCurrent?.let {
                            val currentWeatherModel = CurrentWeatherModel(1, it.cityName, it.main.temp)
                            weatherDatabase.currentWeatherDao().insert(currentWeatherModel)

                        }
                    }
                }

            })


        weatherApi
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