package com.data.datasource.seven_days_forecast.local

import com.core.models.Daily

interface WeatherForecastLocalDataSource {
    suspend fun getDailyWeatherForecast(cityId: String): List<Daily>?
}