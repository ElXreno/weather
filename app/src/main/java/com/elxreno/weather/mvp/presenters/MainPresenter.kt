package com.elxreno.weather.mvp.presenters

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
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
import java.util.*
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (hours in 6..17) {
            viewState.updateBackgroundImage(R.drawable.background_day)
            viewState.setStyle(R.style.AppTheme_Day)
        } else {
            viewState.updateBackgroundImage(R.drawable.background_night)
            viewState.setStyle(R.style.AppTheme_Night)
        }
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
                    viewState.showToast(R.string.something_went_wrong)
                }
            }
        }
    }

    fun locationPermissionGranted() {
        requestSingleUpdate()
    }

    @SuppressLint("MissingPermission")
    fun requestSingleUpdate() {
        val locationManager =
            App.applicationComponent.getContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider: String = LocationManager.GPS_PROVIDER

        locationManager.requestSingleUpdate(
            locationProvider,
            object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    Log.d("location", location.toString())
                    updateWeatherInfo(location)
                }

                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            },
            Looper.myLooper()
        )
    }

    fun updateWeatherInfo(location: Location?) {
        if (location != null) {
            weatherApi
                .getWeatherTodayByCoordinates(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<CurrentWeatherDto>() {
                    override fun onComplete() {
                    }

                    override fun onNext(response: CurrentWeatherDto) {
                        currentWeatherDao.upsert(response)
                        viewState.setRefreshing(false)
                    }

                    override fun onError(e: Throwable) {
                        viewState.showToast(e.localizedMessage.orEmpty())
                        viewState.setRefreshing(false)
                    }

                })

            weatherApi
                .getWeatherForecastByCoordinates(location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<ForecastWeatherDto>() {
                    override fun onComplete() {
                    }

                    override fun onNext(forecastWeatherDto: ForecastWeatherDto) {
                        forecastWeatherDao.clearAll()
                        forecastWeatherDao.upsert(forecastWeatherDto)
                        viewState.setRefreshing(false)
                    }

                    override fun onError(e: Throwable) {
                        viewState.showToast(e.localizedMessage.orEmpty())
                        viewState.setRefreshing(false)
                    }

                })
        } else {
            viewState.showToast(R.string.something_went_wrong)
        }
    }

}
