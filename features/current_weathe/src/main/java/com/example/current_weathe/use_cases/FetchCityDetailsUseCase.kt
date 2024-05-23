package com.example.current_weathe.use_cases

import com.data.repository.current_weather.CurrentWeatherRepository
import javax.inject.Inject

class FetchCityDetailsUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository) {
    suspend operator fun invoke(placeId: String) = currentWeatherRepository.fetchCityDetails(placeId = placeId)
}