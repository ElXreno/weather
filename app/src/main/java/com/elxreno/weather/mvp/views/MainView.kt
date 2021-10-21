package com.elxreno.weather.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun showToast(text: Int)
    fun showToast(text: String)
    fun setRefreshing(isRefreshing: Boolean)
    fun setStyle(style: Int)
    fun updateBackgroundImage(drawable: Int)
}