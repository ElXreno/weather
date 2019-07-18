package com.elxreno.weather.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.BuildConfig
import com.elxreno.weather.api.WeatherApi
import com.elxreno.weather.dao.CurrentWeatherDao
import com.elxreno.weather.dto.CurrentWeatherDto
import com.elxreno.weather.dto.WeatherForecastDto
import com.elxreno.weather.mvp.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.util.*
import javax.inject.Inject


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var weatherApi: WeatherApi
    @Inject
    lateinit var currentWeatherDao: CurrentWeatherDao

    private val compositeDisposable = CompositeDisposable()

    init {
        App.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val cityId = 620127
        val authToken = BuildConfig.openWeatherMapKey

        val currentWeatherDisposableObserver = weatherApi
            .getWeatherTodayById(cityId, authToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<CurrentWeatherDto>() {
                override fun onComplete() {
                    Log.w("onComplete", "DONE!")
                }

                override fun onNext(currentWeatherDto: CurrentWeatherDto) {
                    Log.w("onNext", currentWeatherDto.toString())

                    val result = "City: ${currentWeatherDto.cityName}\n" +
                            "Temperature: ${currentWeatherDto.main.temp} °C"

                    viewState.showTodayWeather(result)
                }

                override fun onError(e: Throwable) {
                    Log.w("onError", e)
                }

            })

        compositeDisposable.addAll(currentWeatherDisposableObserver)

        val forecastWeatherDisposableObserver = weatherApi
            .getWeatherForecastById(cityId, authToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<WeatherForecastDto>() {
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

        compositeDisposable.addAll(forecastWeatherDisposableObserver)
    }

    override fun destroyView(view: MainView?) {
        compositeDisposable.dispose()
        super.destroyView(view)
    }

    fun getDate(timeStamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(Date(timeStamp * 1000L))
    }
}