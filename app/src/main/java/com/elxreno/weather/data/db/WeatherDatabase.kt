package com.elxreno.weather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elxreno.weather.data.dao.CurrentWeatherDao
import com.elxreno.weather.data.dao.ForecastWeatherDao
import com.elxreno.weather.data.db.converters.ItemListConverter
import com.elxreno.weather.data.db.converters.WeatherListConverter
import com.elxreno.weather.data.dto.CurrentWeatherDto
import com.elxreno.weather.data.dto.ForecastWeatherDto

@Database(
    entities = [CurrentWeatherDto::class, ForecastWeatherDto::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(WeatherListConverter::class, ItemListConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun forecastWeatherDao(): ForecastWeatherDao
}