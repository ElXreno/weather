package com.elxreno.weather.data.db.converters

import androidx.room.TypeConverter
import com.elxreno.weather.App
import com.elxreno.weather.data.dto.current.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject


class WeatherListConverter {
    @Inject
    lateinit var gson: Gson

    init {
        App.applicationComponent.inject(this)
    }

    @TypeConverter
    fun stringToWeatherList(data: String?): List<Weather> {
        if (data.isNullOrEmpty())
            return Collections.emptyList()

        val listType = object : TypeToken<List<Weather>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun weatherListToString(data: List<Weather>): String = gson.toJson(data)
}