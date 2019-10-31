package com.elxreno.weather.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.IconHelper
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
                val result = "Description: ${it.weather.first().description.capitalize()}\n" +
                        "Temperature: ${it.main.temp} °C\n" +
                        "Wind speed: ${it.wind.speed} m/s\n" +
                        "Clouds: ${it.clouds.all}%\n" +
                        "Humidity: ${it.main.humidity}%\n" +
                        "Pressure: ${it.main.pressure} hPa\n" +
                        "Rain 1h: ${it.rain?.h1} mm\n" +
                        "Rain 3h: ${it.rain?.h3} mm\n" +
                        "Snow 1h: ${it.snow?.h1} mm\n" +
                        "Snow 3h: ${it.snow?.h3} mm"

                val iconId = it.weather.first().id
                val isDay = (System.currentTimeMillis() / 1000L) <= it.sys.sunset
                val icon = IconHelper().getDrawable(iconId, isDay)

                viewState.showLocation("${it.cityName}, ${it.sys.country}")
                viewState.showTodayWeather(result)
                viewState.updateIcon(icon)
            }
        }
    }

}
