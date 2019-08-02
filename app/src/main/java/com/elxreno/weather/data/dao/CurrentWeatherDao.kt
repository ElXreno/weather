package com.elxreno.weather.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elxreno.weather.data.dto.CURRENT_WEATHER_ID
import com.elxreno.weather.data.dto.CurrentWeatherDto

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(currentWeather: CurrentWeatherDto)

    @Query("SELECT * FROM current_weather WHERE _id = $CURRENT_WEATHER_ID")
    fun getLast(): LiveData<CurrentWeatherDto?>
}