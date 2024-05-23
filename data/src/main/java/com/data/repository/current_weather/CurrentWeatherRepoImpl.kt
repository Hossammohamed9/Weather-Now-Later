package com.data.repository.current_weather

import com.core.exception.ExceptionMapper
import com.core.exception.WeatherException
import com.core.models.CityDetails
import com.core.models.Weather
import com.core.result.Resource
import com.data.datasource.current_weather.local.CurrentWeatherLocalDataSource
import com.data.datasource.current_weather.remote.CurrentWeatherRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherRepoImpl @Inject constructor(
    private val currentWeatherRemoteDatasource: CurrentWeatherRemoteDatasource,
    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource
) : CurrentWeatherRepository {

    override suspend fun fetchCityDetails(placeId: String): Flow<Resource<CityDetails>> =
        flow {
            emit(Resource.Loading(true))
            emit(currentWeatherRemoteDatasource.fetchCityDetails(placeId))
        }

    override suspend fun getCurrentWeather(
        cityId: String,
        lat: Double,
        lon: Double
    ): Flow<Resource<Weather>> =
        flow {
            emit(Resource.Loading(true))
            val weather = currentWeatherLocalDataSource.getCurrentWeather(cityId)
            if (weather == null) {
                fetchCurrentWeather(cityId, lat, lon)
                currentWeatherLocalDataSource.getCurrentWeather(cityId)?.let {
                    emit(Resource.Success(it))
                }
                    ?: emit(Resource.Failure(WeatherException.CustomException("Error occurred, please check your connection and try again")))
            } else {
                emit(Resource.Success(weather))
            }
        }.catch {
            emit(Resource.Failure(ExceptionMapper.mapExceptionToWeatherException(it as Exception)))
        }


    override suspend fun fetchCurrentWeather(
        cityId: String,
        lat: Double,
        lon: Double
    ) {
        currentWeatherRemoteDatasource.getWeather(cityId, lat, lon).also {
            currentWeatherLocalDataSource.saveLatestWeather(it)
        }
    }

    override suspend fun deleteCurrentSavedCity() {
        currentWeatherLocalDataSource.deleteCurrentCity()
    }

}