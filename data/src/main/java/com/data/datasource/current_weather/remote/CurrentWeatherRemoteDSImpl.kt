package com.data.datasource.current_weather.remote

import com.core.exception.ExceptionMapper
import com.core.models.CityDetails
import com.core.models.Weather
import com.core.result.Resource
import com.core.utils.Constants.APP_ID
import com.data.mappers.toDomain
import com.data.network.api.CurrentWeatherApi
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CurrentWeatherRemoteDSImpl @Inject constructor(
    private val currentWeatherApi: CurrentWeatherApi,
    private val placesClient: PlacesClient
) : CurrentWeatherRemoteDatasource {

    override suspend fun fetchCityDetails(placeId: String): Resource<CityDetails> {
        val placeFields = listOf(Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ID)
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()
        return suspendCoroutine { continuation ->
            placesClient.fetchPlace(request).addOnSuccessListener {
                continuation.resume(Resource.Success(it.place.toDomain()))
            }.addOnFailureListener {
                continuation.resume(Resource.Failure(ExceptionMapper.mapExceptionToWeatherException(it)))
            }
        }
    }


    override suspend fun getWeather(cityId: String, lat: Double, lon: Double): Weather =
        currentWeatherApi.getCurrentWeather(lat, lon, APP_ID).toDomain(cityId)

}