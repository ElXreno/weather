package com.elxreno.weather.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun showToast(text: Int, runOnUiThread: Boolean = false)
    fun showToast(text: String, runOnUiThread: Boolean = false)
    fun setRefreshing(isRefreshing: Boolean)
}