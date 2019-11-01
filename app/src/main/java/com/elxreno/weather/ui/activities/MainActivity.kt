package com.elxreno.weather.ui.activities

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.elxreno.weather.R
import com.elxreno.weather.mvp.presenters.MainPresenter
import com.elxreno.weather.mvp.views.MainView
import com.elxreno.weather.ui.adapters.PagerAdapter
import com.elxreno.weather.ui.fragments.CurrentFragment
import com.elxreno.weather.ui.fragments.ForecastFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : MvpAppCompatActivity(), MainView, SwipeRefreshLayout.OnRefreshListener {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onRefresh() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                10
            )
        } else {
            presenter.locationPermissionGranted()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(CurrentFragment.newInstance(), getString(R.string.tab_today))
        adapter.addFragment(ForecastFragment.newInstance(), getString(R.string.tab_forecast))

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, v: Float, i1: Int) {}

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {
                swipeToRefresh.isEnabled = state == ViewPager.SCROLL_STATE_IDLE
            }
        })

        tabs.setupWithViewPager(viewPager)

        swipeToRefresh.setOnRefreshListener(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun showToast(text: Int, runOnUiThread: Boolean) {
        if (runOnUiThread) runOnUiThread { toast(text) } else toast(text)
    }

    override fun showToast(text: String, runOnUiThread: Boolean) {
        if (runOnUiThread) runOnUiThread { toast(text) } else toast(text)
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        swipeToRefresh.isRefreshing = isRefreshing
    }

    override fun setStyle(style: Int) {
        setTheme(style)
    }

    override fun updateBackgroundImage(drawable: Int) {
        backgroundImage.setImageResource(drawable)
    }
}