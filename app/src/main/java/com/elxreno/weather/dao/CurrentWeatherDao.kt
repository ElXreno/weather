package com.elxreno.weather.dao

import androidx.room.*
import com.elxreno.weather.databases.models.CurrentWeatherModel

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeather: CurrentWeatherModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(currentWeather: CurrentWeatherModel)

    @Delete
    fun delete(currentWeather: CurrentWeatherModel)

    @Query("SELECT * FROM current_weather WHERE id = :id")
    fun getById(id: Int): CurrentWeatherModel?

    @Query("SELECT * FROM current_weather LIMIT 1")
    fun getOne(): CurrentWeatherModel?
}