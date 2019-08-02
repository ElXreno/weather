package com.elxreno.weather.mvp.presenters

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elxreno.weather.App
import com.elxreno.weather.R
import com.elxreno.weather.data.api.WeatherApi
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.data.dao.ForecastWeatherDao
import com.elxreno.weather.data.dto.CurrentWeatherDto
import com.elxreno.weather.data.dto.ForecastWeatherDto
import com.elxreno.weather.mvp.views.MainView
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var weatherApi: WeatherApi
    @Inject
    lateinit var currentWeatherDao: CurrentWeatherDao
    @Inject
    lateinit var forecastWeatherDao: ForecastWeatherDao

    init {
        App.applicationComponent.inject(this)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty()) {
            when (requestCode) {
                10 -> {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        locationPermissionGranted()
                    } else {
                        viewState.showToast(R.string.cannot_determine_user_location)
                    }
                }

                else -> {
                    // Nothing
                }
            }
        }
    }

    fun locationPermissionGranted() {
        val location = getLocation()
        Log.d("location", location.toString())
        updateWeatherInfo(location)
    }

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        val locationManager =
            App.applicationComponent.getContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider: String = LocationManager.NETWORK_PROVIDER

        return locationManager.getLastKnownLocation(locationProvider)
    }

    fun updateWeatherInfo(location: Location?) {
        if (location != null) {
            weatherApi
                .getWeatherTodayByCoordinates(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<CurrentWeatherDto>() {
                    override fun onComplete() {
                        Log.w("onComplete", "DONE!")
                    }

                    override fun onNext(response: CurrentWeatherDto) {
                        Log.w("onNext", response.toString())
                        currentWeatherDao.upsert(response)
                    }

                    override fun onError(e: Throwable) {
                        Log.w("onError", e)
                    }

                })

            weatherApi
                .getWeatherForecastByCoordinates(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<ForecastWeatherDto>() {
                    override fun onComplete() {
                        Log.w("onComplete", "DONE!")
                    }

                    override fun onNext(forecastWeatherDto: ForecastWeatherDto) {
                        Log.w("onNext", forecastWeatherDto.toString())
                        forecastWeatherDao.upsert(forecastWeatherDto)
                    }

                    override fun onError(e: Throwable) {
                        Log.w("onError", e)
                    }

                })
        } else {
            viewState.showToast(R.string.something_went_wrong)
        }
    }

}