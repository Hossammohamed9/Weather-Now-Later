package com.data.datasource.current_weather.local

import com.core.models.Weather
import com.data.database.dao.WeatherDAO
import com.data.mappers.toDomain
import com.data.mappers.toEntity
import javax.inject.Inject

class CurrentWeatherLocalDSImpl @Inject constructor(private val weatherDAO: WeatherDAO):
    CurrentWeatherLocalDataSource {
    override suspend fun saveLatestWeather(weather: Weather) {
        weatherDAO.deleteAll()
        weatherDAO.insertWeatherOfLocation(weather.toEntity())
    }

    override suspend fun getCurrentWeather(cityId: String): Weather? =
        weatherDAO.getWeatherForCity(cityId).toDomain()

    override suspend fun deleteCurrentCity() {
        weatherDAO.deleteAll()
    }

}