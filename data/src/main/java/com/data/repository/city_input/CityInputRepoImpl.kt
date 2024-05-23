package com.data.repository.city_input

import com.core.exception.ExceptionMapper
import com.core.exception.WeatherException
import com.core.models.Weather
import com.core.result.Resource
import com.data.datasource.city_input.local.CityInputLocalDataSource
import com.data.datasource.city_input.remote.CityInputRemoteDataSource
import com.google.android.libraries.places.api.model.AutocompletePrediction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CityInputRepoImpl @Inject constructor(
    private val cityInputRemoteDataSource: CityInputRemoteDataSource,
    private val cityInputLocalDataSource: CityInputLocalDataSource
): CityInputRepository {

    override suspend fun searchCities(query: String): Flow<Resource<List<AutocompletePrediction>>> = flow{
        emit(Resource.Loading(true))
        emit(cityInputRemoteDataSource.getCities(query))
    }

    override suspend fun getSavedCity(): Flow<Resource<Weather>> =
        flow {
            emit(Resource.Loading(true))
            val city = cityInputLocalDataSource.getSaveCity()
            city?.let {
                emit(Resource.Success(it))
            } ?: emit(Resource.Failure(WeatherException.CustomException("no data")))
        }.catch {
            emit(Resource.Failure(ExceptionMapper.mapExceptionToWeatherException(it as Exception)))
        }


}