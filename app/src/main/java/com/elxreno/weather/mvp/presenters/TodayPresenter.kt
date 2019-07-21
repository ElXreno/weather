package com.elxreno.weather.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.AppConstants
import com.elxreno.weather.data.api.WeatherApi
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.data.db.entities.CurrentWeatherEntity
import com.elxreno.weather.dto.CurrentWeatherDto
import com.elxreno.weather.mvp.views.TodayView
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class TodayPresenter : MvpPresenter<TodayView>() {
    @Inject
    lateinit var weatherApi: WeatherApi
    @Inject
    lateinit var currentWeatherDao: CurrentWeatherDao

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        currentWeatherDao.getLast().observeForever {
            it?.let {
                val result = "City: ${it.city}\n" +
                        "Temperature: ${it.temp} °C\n" +
                        "Wind speed: ${it.windSpeed} m/s"

                viewState.showTodayWeather(result)
            }
        }

        weatherApi
            .getWeatherTodayById(AppConstants.CITY_ID)
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<CurrentWeatherDto>() {
                override fun onComplete() {
                    Log.w("onComplete", "DONE!")
                }

                override fun onNext(currentWeatherDto: CurrentWeatherDto) {
                    Log.w("onNext", currentWeatherDto.toString())
                    currentWeatherDao.upsert(
                        CurrentWeatherEntity(
                            currentWeatherDto.cityName,
                            currentWeatherDto.main.temp,
                            currentWeatherDto.wind.speed
                        )
                    )
                }

                override fun onError(e: Throwable) {
                    Log.w("onError", e)
                }

            })
    }

}