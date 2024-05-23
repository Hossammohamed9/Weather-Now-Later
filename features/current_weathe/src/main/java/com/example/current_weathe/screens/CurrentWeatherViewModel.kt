package com.example.current_weathe.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.result.Resource
import com.example.current_weathe.use_cases.FetchCityDetailsUseCase
import com.example.current_weathe.use_cases.FetchCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val fetchCityDetailsUseCase: FetchCityDetailsUseCase,
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state : MutableState<CurrentWeatherViewState> = mutableStateOf(
        CurrentWeatherViewState.Loading
    )
    val state : State<CurrentWeatherViewState> get() = _state

    init {
        val cityId: String = savedStateHandle.get<String>("cityId")!!
        fetchCityDetails(cityId)
    }

    fun fetchCityDetails(cityId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCityDetailsUseCase(cityId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _state.value = CurrentWeatherViewState.Loading
                        }

                        is Resource.Success -> {
                            getCurrentWeatherForCity(cityId, resource.data.destination.latitude, resource.data.destination.longitude)
                        }

                        is Resource.Failure -> {
                            _state.value = CurrentWeatherViewState.Error(
                                resource.exception.message ?: "Unknown error"
                            )
                        }
                    }
                }

            }
        }
    }

    private suspend fun getCurrentWeatherForCity(cityId: String, latitude: Double, longitude: Double){
        withContext(Dispatchers.IO) {
            fetchCurrentWeatherUseCase(
                cityId,
                latitude,
                longitude
            ).collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is Resource.Loading -> _state.value = CurrentWeatherViewState.Loading
                        is Resource.Success -> _state.value =
                            CurrentWeatherViewState.Success(it.data)
                        is Resource.Failure -> _state.value =
                            CurrentWeatherViewState.Error(it.exception.message ?: "Unknown error")
                    }
                }
            }
        }
    }

}