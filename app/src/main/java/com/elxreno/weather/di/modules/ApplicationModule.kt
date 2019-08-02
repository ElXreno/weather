package com.elxreno.weather.di.modules

import android.app.Application
import com.elxreno.weather.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}