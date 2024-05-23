package com.example.weathernowandlater.navigation

import com.features.current_weather.navigation.CurrentWeatherFeatureApi
import com.features.input_city.navigation.CityInputFeatureApi
import com.features.seven_days_forecast.navigation.WeatherForecastFeatureApi

data class NavigationProvider(
    val cityInputApi: CityInputFeatureApi,
    val currentWeatherApi: CurrentWeatherFeatureApi,
    val weatherForecastApi: WeatherForecastFeatureApi
)
