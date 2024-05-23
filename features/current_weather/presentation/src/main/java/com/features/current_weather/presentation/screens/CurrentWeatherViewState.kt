package com.features.current_weather.presentation.screens

import com.core.models.Weather

sealed class CurrentWeatherViewState {
    data object Loading : CurrentWeatherViewState()
    data class Success(val weather: Weather) : CurrentWeatherViewState()
    data class Error(val errorMessage: String) : CurrentWeatherViewState()
}