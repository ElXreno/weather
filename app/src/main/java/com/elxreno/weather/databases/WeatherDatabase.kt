package com.elxreno.weather.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elxreno.weather.dao.CurrentWeatherDao
import com.elxreno.weather.databases.models.CurrentWeatherModel

@Database(entities = [CurrentWeatherModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

}