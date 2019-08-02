package com.elxreno.weather.di.components

import android.content.Context
import com.elxreno.weather.data.api.WeatherApi
import com.elxreno.weather.data.db.converters.ItemListConverter
import com.elxreno.weather.data.db.converters.WeatherListConverter
import com.elxreno.weather.di.modules.*
import com.elxreno.weather.mvp.presenters.ForecastPresenter
import com.elxreno.weather.mvp.presenters.TodayPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ContextModule::class, NetworkingModule::class, ApiModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun getWeatherApi(): WeatherApi
    fun getContext(): Context

    fun inject(forecastPresenter: ForecastPresenter)
    fun inject(todayPresenter: TodayPresenter)
    fun inject(weatherListConverter: WeatherListConverter)
    fun inject(itemListConverter: ItemListConverter)

}