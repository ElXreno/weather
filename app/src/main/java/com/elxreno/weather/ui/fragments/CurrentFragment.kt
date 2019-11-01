package com.elxreno.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.elxreno.weather.R
import com.elxreno.weather.mvp.presenters.CurrentPresenter
import com.elxreno.weather.mvp.views.CurrentView
import kotlinx.android.synthetic.main.fragment_current.*

class CurrentFragment : MvpAppCompatFragment(), CurrentView {

    @InjectPresenter
    lateinit var presenter: CurrentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    companion object {
        fun newInstance(): CurrentFragment =
            CurrentFragment()
    }

    override fun updateIcon(drawable: Int) {
        weatherIcon.setImageResource(drawable)
    }

    override fun updateInfo(
        location: String,
        temperature: String,
        cloudiness: String,
        windSpeed: String,
        windDirection: String,
        pressure: String,
        humidity: String
    ) {
        locationInfo.text = location
        temperatureInfo.text = temperature
        cloudInfo.text = cloudiness
        windSpeedInfo.text = windSpeed
        windDirectionInfo.text = windDirection
        pressureInfo.text = pressure
        humidityInfo.text = humidity
    }
}