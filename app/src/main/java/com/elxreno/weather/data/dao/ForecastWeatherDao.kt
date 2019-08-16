package com.elxreno.weather.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elxreno.weather.data.dto.FORECAST_WEATHER_ID
import com.elxreno.weather.data.dto.ForecastWeatherDto

@Dao
interface ForecastWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(currentWeather: ForecastWeatherDto)

    @Query("SELECT * FROM forecast_weather WHERE _id = $FORECAST_WEATHER_ID")
    fun getLast(): LiveData<ForecastWeatherDto?>

    @Query("DELETE FROM forecast_weather")
    fun clearAll()
}