package com.data.database

import androidx.room.TypeConverter
import com.core.models.Current
import com.core.models.Daily
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class   WeatherTypeConverters {

    @TypeConverter
    fun fromCurrentToString(current: Current): String {
        return Gson().toJson(current)
    }

    @TypeConverter
    fun toCurrentFromString(json: String): Current {
        return Gson().fromJson(json, Current::class.java)
    }

    @TypeConverter
    fun fromDailyListToString(dailyList: List<Daily>): String {
        return Gson().toJson(dailyList)
    }

    @TypeConverter
    fun toDailyListFromString(json: String): List<Daily> {
        return Gson().fromJson(json, object : TypeToken<List<Daily>>() {}.type)
    }

}