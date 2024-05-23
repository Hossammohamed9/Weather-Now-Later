package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.data.database.dao.WeatherDAO
import com.data.database.models.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(WeatherTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDAO

}