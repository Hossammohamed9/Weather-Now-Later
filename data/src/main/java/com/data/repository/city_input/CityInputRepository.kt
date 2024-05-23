package com.data.repository.city_input

import com.core.models.Weather
import com.core.result.Resource
import com.google.android.libraries.places.api.model.AutocompletePrediction
import kotlinx.coroutines.flow.Flow

interface CityInputRepository {

    suspend fun searchCities(query: String): Flow<Resource<List<AutocompletePrediction>>>
    suspend fun getSavedCity(): Flow<Resource<Weather>>
}