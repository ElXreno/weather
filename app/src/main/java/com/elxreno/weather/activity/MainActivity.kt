package com.elxreno.weather.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elxreno.weather.PagerAdapter
import com.elxreno.weather.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(this, supportFragmentManager)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}