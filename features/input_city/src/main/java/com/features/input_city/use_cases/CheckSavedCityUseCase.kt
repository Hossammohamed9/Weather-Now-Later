package com.features.input_city.use_cases

import com.data.repository.city_input.CityInputRepository
import javax.inject.Inject

class CheckSavedCityUseCase @Inject constructor(private val inputCityInputRepository: CityInputRepository) {
    suspend operator fun invoke() = inputCityInputRepository.getSavedCity()
}