package com.elxreno.weather.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elxreno.weather.data.db.entities.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(currentWeather: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather LIMIT 1")
    fun getLast(): LiveData<CurrentWeatherEntity?>
}