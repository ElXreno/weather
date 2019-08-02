package com.elxreno.weather.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
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
                        "Temperature: ${it.main.temp} °C\n" +
                        "Wind speed: ${it.wind.speed} m/s"

                viewState.showTodayWeather(result)
            }
        }
    }

}