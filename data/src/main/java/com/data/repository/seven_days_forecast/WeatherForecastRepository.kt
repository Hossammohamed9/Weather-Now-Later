package com.data.repository.seven_days_forecast

import com.core.models.Daily
import com.core.result.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(cityId: String): Flow<Resource<List<Daily>>>
}