package com.elxreno.weather.di.components

import android.content.Context
import com.elxreno.weather.api.WeatherApi
import com.elxreno.weather.di.modules.*
import com.elxreno.weather.mvp.presenters.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ContextModule::class, NetworkingModule::class, ApiModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun getWeatherApi(): WeatherApi
    fun getContext(): Context

    fun inject(mainPresenter: MainPresenter)

}