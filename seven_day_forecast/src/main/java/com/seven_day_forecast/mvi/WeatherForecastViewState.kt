package com.seven_day_forecast.mvi

import com.core.models.Daily

sealed class WeatherForecastViewState {
    data object Loading : WeatherForecastViewState()
    data class Success(val dailyWeatherList: List<Daily>) : WeatherForecastViewState()
    data class Error(val errorMessage: String) : WeatherForecastViewState()
}