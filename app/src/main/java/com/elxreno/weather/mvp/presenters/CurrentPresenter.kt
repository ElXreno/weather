package com.elxreno.weather.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.AppConstants
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.mvp.views.CurrentView
import javax.inject.Inject

@InjectViewState
class CurrentPresenter : MvpPresenter<CurrentView>() {

    @Inject
    lateinit var currentWeatherDao: CurrentWeatherDao

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        currentWeatherDao.getLast().observeForever { response ->
            response?.let {
                val result = "City: ${it.cityName}\n" +
                        "Country: ${it.sys.country}\n" +
                        "Description: ${it.weather.first().description}\n" +
                        "Temperature: ${it.main.temp} °C\n" +
                        "Wind speed: ${it.wind.speed} m/s\n" +
                        "Clouds: ${it.clouds.all}%\n" +
                        "Humidity: ${it.main.humidity}%\n" +
                        "Pressure: ${it.main.pressure} hPa\n" +
                        "Rain 1h: ${it.rain?.h1} mm\n" +
                        "Rain 3h: ${it.rain?.h3} mm\n" +
                        "Snow 1h: ${it.snow?.h1} mm\n" +
                        "Snow 3h: ${it.snow?.h3} mm"

                viewState.showTodayWeather(result)
                viewState.updateIcon(
                    "${AppConstants.OPENWEATHERMAP_ICON_BASE}${it.weather.first().icon}@2x.png"
                )
            }
        }
    }

}
