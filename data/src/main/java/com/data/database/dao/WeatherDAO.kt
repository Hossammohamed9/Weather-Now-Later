package com.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.database.models.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherOfLocation(weather: WeatherEntity)

    @Query("SELECT * FROM weather_table WHERE cityId= :cityId")
    suspend fun getWeatherForCity(cityId: String): WeatherEntity?

    @Query("SELECT * FROM weather_table")
    suspend fun getSavedCity(): WeatherEntity?

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}