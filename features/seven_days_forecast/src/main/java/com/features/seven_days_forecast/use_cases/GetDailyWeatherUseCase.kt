package com.features.seven_days_forecast.use_cases

import com.data.repository.seven_days_forecast.WeatherForecastRepository
import javax.inject.Inject

class GetDailyWeatherUseCase @Inject constructor(private val weatherForecastRepository: WeatherForecastRepository) {
    suspend operator fun invoke(cityId: String) = weatherForecastRepository.getWeatherForecast(cityId)
}