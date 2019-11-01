package com.elxreno.weather.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrentView : MvpView {
    fun updateIcon(drawable: Int)
    fun updateInfo(
        location: String,
        temperature: String,
        cloudiness: String,
        windSpeed: String,
        windDirection: String,
        pressure: String,
        humidity: String
    )
}