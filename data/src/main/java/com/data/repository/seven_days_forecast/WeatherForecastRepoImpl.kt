package com.data.repository.seven_days_forecast

import com.core.exception.ExceptionMapper
import com.core.exception.WeatherException
import com.core.models.Daily
import com.core.result.Resource
import com.data.datasource.seven_days_forecast.local.WeatherForecastLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherForecastRepoImpl @Inject constructor(private val weatherForecastLocalDataSource: WeatherForecastLocalDataSource):
    WeatherForecastRepository {

    override suspend fun getWeatherForecast(cityId: String): Flow<Resource<List<Daily>>> = flow {
        emit(Resource.Loading(true))
        val weatherList = weatherForecastLocalDataSource.getDailyWeatherForecast(cityId)
        weatherList?.let {
            emit(Resource.Success(it))
        } ?: emit(Resource.Failure(WeatherException.CustomException("Error occurred, please check your connection and try again")))

    }.catch {
        emit(Resource.Failure(ExceptionMapper.mapExceptionToWeatherException(it as Exception)))
    }
}