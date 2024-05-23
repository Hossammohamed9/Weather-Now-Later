package com.data.datasource.current_weather.remote

import com.core.models.CityDetails
import com.core.models.Weather
import com.core.result.Resource


interface CurrentWeatherRemoteDatasource {
    suspend fun fetchCityDetails(placeId: String): Resource<CityDetails>
    suspend fun getWeather(cityId: String, lat: Double, lon: Double): Weather
}