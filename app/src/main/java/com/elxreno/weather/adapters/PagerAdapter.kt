package com.elxreno.weather.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.elxreno.weather.R
import com.elxreno.weather.fragments.ForecastFragment
import com.elxreno.weather.fragments.TodayFragment

class PagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> TodayFragment.newInstance()
        1 -> ForecastFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> context.resources.getString(R.string.tab_today)
        1 -> context.resources.getString(R.string.tab_forecast)
        else -> ""
    }

    override fun getCount(): Int = 2
}