package com.features.city_input.presentation.screens

import com.google.android.libraries.places.api.model.AutocompletePrediction

sealed class CityInputViewState{
    data object Idle: CityInputViewState()
    data object Loading: CityInputViewState()
    data class Error(val errorMessage: String): CityInputViewState()
    data class Success(val cities: List<AutocompletePrediction>): CityInputViewState()
}
