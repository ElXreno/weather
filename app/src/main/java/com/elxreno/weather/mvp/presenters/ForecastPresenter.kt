package com.elxreno.weather.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.data.dao.ForecastWeatherDao
import com.elxreno.weather.mvp.views.ForecastView
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class ForecastPresenter : MvpPresenter<ForecastView>() {

    @Inject
    lateinit var forecastWeatherDao: ForecastWeatherDao

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        forecastWeatherDao.getLast().observeForever { response ->
            response?.let {
                var result = ""

                it.list.forEach { item ->
                    val date = getDate(item.timestamp)

                    result += "\nDate: $date\n" +
                            "Minimal temperature: ${item.main.minTemp} °C\n" +
                            "Maximum temperature: ${item.main.maxTemp} °C\n" +
                            "Clouds: ${item.clouds.all}%\n" +
                            "Pressure: ${item.main.pressure} hPa\n" +
                            "Humidity: ${item.main.humidity}%\n" +
                            "Rain: ${item.rain?.h3} mm\n" +
                            "Snow: ${item.snow?.h3} mm\n" +
                            "Wind speed: ${item.wind.speed} m/s\n"
                }

                viewState.showForecastWeather(result)
            }
        }
    }

    private fun getDate(timeStamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
            .format(Date(timeStamp * 1000L))
    }

}