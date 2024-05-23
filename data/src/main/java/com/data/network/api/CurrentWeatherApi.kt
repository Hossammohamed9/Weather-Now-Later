package com.data.network.api

import com.data.network.models.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("data/2.5/onecall")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): WeatherDTO

}