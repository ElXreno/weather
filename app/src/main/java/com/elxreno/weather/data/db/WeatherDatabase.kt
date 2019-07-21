package com.elxreno.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.data.db.entities.CurrentWeatherEntity

@Database(entities = [CurrentWeatherEntity::class], exportSchema = false, version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}