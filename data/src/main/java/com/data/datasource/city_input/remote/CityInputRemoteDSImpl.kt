package com.data.datasource.city_input.remote

import com.core.exception.ExceptionMapper
import com.core.result.Resource
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CityInputRemoteDSImpl @Inject constructor(
    private val placesClient: PlacesClient
): CityInputRemoteDataSource {
    private val autoCompleteToken = AutocompleteSessionToken.newInstance()

    override suspend fun getCities(query: String): Resource<List<AutocompletePrediction>> {
        val request = FindAutocompletePredictionsRequest.builder().setSessionToken(autoCompleteToken)
            .setQuery(query).setTypesFilter(listOf(PlaceTypes.CITIES)).build()

        return suspendCoroutine { continuation ->
            placesClient.findAutocompletePredictions(request).addOnSuccessListener {
                continuation.resume(Resource.Success(it.autocompletePredictions))
            }.addOnFailureListener{
                continuation.resume(Resource.Failure(ExceptionMapper.mapExceptionToWeatherException(it)))
            }
        }
    }
}