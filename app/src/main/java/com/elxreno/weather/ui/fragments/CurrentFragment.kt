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

    override fun showTodayWeather(text: String) {
        currentWeatherInfo.text = text
    }

    override fun showLocation(text: String) {
        currentWeatherLocation.text = text
    }

    override fun updateIcon(drawable: Int) {
        currentWeatherIcon.setImageResource(drawable)
    }
}