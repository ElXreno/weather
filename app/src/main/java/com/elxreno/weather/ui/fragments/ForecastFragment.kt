package com.elxreno.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.elxreno.weather.R
import com.elxreno.weather.mvp.presenters.ForecastPresenter
import com.elxreno.weather.mvp.views.ForecastView
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : MvpAppCompatFragment(), ForecastView {

    @InjectPresenter
    lateinit var presenter: ForecastPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    companion object {
        fun newInstance(): ForecastFragment =
            ForecastFragment()
    }

    override fun showForecastWeather(text: String) {
        forecastWeather.text = text
    }
}