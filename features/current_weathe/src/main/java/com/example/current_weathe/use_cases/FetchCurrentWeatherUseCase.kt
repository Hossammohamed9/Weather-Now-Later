package com.example.current_weathe.use_cases

import com.data.repository.current_weather.CurrentWeatherRepository
import javax.inject.Inject

class FetchCurrentWeatherUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository){
    suspend operator fun invoke(cityId: String, lat: Double, lon: Double) = currentWeatherRepository.getCurrentWeather(cityId, lat, lon)
}