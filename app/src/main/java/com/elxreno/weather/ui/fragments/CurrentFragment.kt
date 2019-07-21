package com.elxreno.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.elxreno.weather.R
import com.elxreno.weather.mvp.presenters.TodayPresenter
import com.elxreno.weather.mvp.views.TodayView
import kotlinx.android.synthetic.main.fragment_current.*

class CurrentFragment : MvpAppCompatFragment(), TodayView {
    @InjectPresenter
    lateinit var presenter: TodayPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    companion object {
        fun newInstance(): CurrentFragment =
            CurrentFragment()
    }

    override fun showTodayWeather(text: String) {
        todayWeather.text = text
    }
}