package com.data.datasource.current_weather.local

import com.core.models.Weather

interface CurrentWeatherLocalDataSource {
    suspend fun saveLatestWeather(weather: Weather)
    suspend fun getCurrentWeather(cityId: String): Weather?
    suspend fun deleteCurrentCity()
}