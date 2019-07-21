package com.elxreno.weather.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.AppConstants
import com.elxreno.weather.data.api.WeatherApi
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.dto.WeatherForecastDto
import com.elxreno.weather.mvp.views.ForecastView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class ForecastPresenter : MvpPresenter<ForecastView>() {
    @Inject
    lateinit var weatherApi: WeatherApi
    @Inject
    lateinit var currentWeatherDao: CurrentWeatherDao

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        weatherApi
            .getWeatherForecastById(AppConstants.CITY_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<WeatherForecastDto>() {
                override fun onComplete() {
                    Log.w("onComplete", "DONE!")
                }

                override fun onNext(weatherForecastDto: WeatherForecastDto) {
                    Log.w("onNext", weatherForecastDto.toString())

                    var result = ""

                    weatherForecastDto.list.forEach {
                        val date = getDate(it.timestamp)

                        result += "\nDate: $date\n" +
                                "Minimal temperature: ${it.main.minTemp} °C\n" +
                                "Maximum temperature: ${it.main.maxTemp} °C\n" +
                                "Wind speed: ${it.wind.speed} m/s\n"
                    }

                    viewState.showForecastWeather(result)
                }

                override fun onError(e: Throwable) {
                    Log.w("onError", e)
                }

            })
    }

    fun getDate(timeStamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(Date(timeStamp * 1000L))
    }

}