package com.elxreno.weather.ui.activities

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.elxreno.weather.R
import com.elxreno.weather.ui.adapters.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(this, supportFragmentManager)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)
    }
}