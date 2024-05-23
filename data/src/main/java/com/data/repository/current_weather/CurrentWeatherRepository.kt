package com.data.repository.current_weather

import com.core.models.CityDetails
import com.core.models.Weather
import com.core.result.Resource
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun fetchCityDetails(placeId: String): Flow<Resource<CityDetails>>
    suspend fun getCurrentWeather(cityId: String, lat: Double, lon: Double): Flow<Resource<Weather>>
    suspend fun fetchCurrentWeather(cityId: String, lat: Double, lon: Double)
    suspend fun deleteCurrentSavedCity()
}