package com.elxreno.weather

import android.app.Application
import com.elxreno.weather.di.components.ApplicationComponent
import com.elxreno.weather.di.components.DaggerApplicationComponent
import com.elxreno.weather.di.modules.ContextModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

}