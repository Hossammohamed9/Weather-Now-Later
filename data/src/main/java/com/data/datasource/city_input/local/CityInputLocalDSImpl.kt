package com.data.datasource.city_input.local

import com.core.models.Weather
import com.data.database.dao.WeatherDAO
import com.data.mappers.toDomain
import javax.inject.Inject

class CityInputLocalDSImpl @Inject constructor(private val weatherDAO: WeatherDAO) : CityInputLocalDataSource {
    override suspend fun getSaveCity(): Weather? {
        return weatherDAO.getSavedCity().toDomain()
    }
}