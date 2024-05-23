package com.features.current_weather.use_cases

import com.data.repository.current_weather.CurrentWeatherRepository
import javax.inject.Inject

class DeleteCurrentSavedCityUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository) {
    suspend operator fun invoke() {
        currentWeatherRepository.deleteCurrentSavedCity()
    }
}