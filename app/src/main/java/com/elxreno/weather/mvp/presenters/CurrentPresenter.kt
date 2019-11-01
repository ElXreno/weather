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
                val iconId = it.weather.first().id
                val isDay = (System.currentTimeMillis() / 1000L) <= it.sys.sunset
                val icon = IconHelper().getDrawable(iconId, isDay)

                viewState.updateInfo(
                    String.format("%s, %s", it.cityName, it.sys.country),
                    String.format(
                        "%.0f °C | %s",
                        it.main.temp,
                        it.weather.first().description
                    ),
                    String.format("%d%%", it.clouds.all),
                    String.format("%.0f m/s", it.wind.speed),
                    String.format("%d", it.wind.deg),
                    String.format("%.0f hPa", it.main.pressure),
                    String.format("%.0f%%", it.main.humidity)
                )
                viewState.updateIcon(icon)
            }
        }
    }

}
