package com.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.core.models.Current
import com.core.models.Daily
import com.core.utils.Constants.WEATHER_TABLE

@Entity(tableName = WEATHER_TABLE)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val cityId: String,
    val lat: Double,
    val lng: Double,
    val timezone: String,
    val current: Current,
    val nextSevenDays: List<Daily>
)
