package com.features.city_input.domain.use_cases

import com.data.repository.city_input.CityInputRepository
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(private val cityInputRepository: CityInputRepository) {
    suspend operator fun invoke(query: String) = cityInputRepository.searchCities(query)
}