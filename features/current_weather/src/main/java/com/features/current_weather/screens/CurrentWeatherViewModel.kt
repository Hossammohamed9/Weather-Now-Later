package com.features.current_weather.screens

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.dispatchers.DispatcherProvider
import com.core.result.Resource
import com.features.current_weather.use_cases.DeleteCurrentSavedCityUseCase
import com.features.current_weather.use_cases.FetchCityDetailsUseCase
import com.features.current_weather.use_cases.FetchCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val fetchCityDetailsUseCase: FetchCityDetailsUseCase,
    private val fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase,
    private val deleteCurrentSavedCityUseCase: DeleteCurrentSavedCityUseCase,
    private val dispatcherProvider: DispatcherProvider,
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
        viewModelScope.launch(dispatcherProvider.io) {
            fetchCityDetailsUseCase(cityId).collect { resource ->
                withContext(dispatcherProvider.main) {
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
        withContext(dispatcherProvider.io) {
            fetchCurrentWeatherUseCase(
                cityId,
                latitude,
                longitude
            ).collect {
                withContext(dispatcherProvider.main) {
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

    fun deleteCurrentCity(){
        viewModelScope.launch(dispatcherProvider.io){
            deleteCurrentSavedCityUseCase()
        }
    }

    fun getCityName(latitude: Double?, longitude: Double?,context: Context): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        var cityName = ""
        try {
            val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude!!, longitude!!, 1)
            if (addresses!!.isNotEmpty()) {
                cityName = addresses[0].subAdminArea
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cityName
    }

}