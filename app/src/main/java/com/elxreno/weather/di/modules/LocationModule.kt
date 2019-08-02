package com.elxreno.weather.di.modules

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationService(activity: Activity) =
        activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
}