package com.elxreno.weather.di.modules

import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationService(context: Context) =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
}