package com.data.datasource.seven_days_forecast.local

import com.core.models.Daily
import com.data.database.dao.WeatherDAO
import javax.inject.Inject

class WeatherForecastLocalDSImpl @Inject constructor(private val weatherDAO: WeatherDAO):
    WeatherForecastLocalDataSource {

    override suspend fun getDailyWeatherForecast(cityId: String): List<Daily>? =
        weatherDAO.getWeatherForCity(cityId)?.nextSevenDays

}