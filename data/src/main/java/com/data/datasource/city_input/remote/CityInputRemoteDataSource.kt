package com.data.datasource.city_input.remote

import com.core.result.Resource
import com.google.android.libraries.places.api.model.AutocompletePrediction

interface CityInputRemoteDataSource {
    suspend fun getCities(query: String): Resource<List<AutocompletePrediction>>
}