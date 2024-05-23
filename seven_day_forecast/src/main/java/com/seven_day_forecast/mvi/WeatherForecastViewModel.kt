package com.seven_day_forecast.mvi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.result.Resource
import com.seven_day_forecast.use_cases.GetDailyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase
) : ViewModel(){

    private val _state : MutableState<WeatherForecastViewState> = mutableStateOf(
        WeatherForecastViewState.Loading
    )
    val state : State<WeatherForecastViewState> get() = _state

    private val intentChannel = Channel<ForecastIntent>(Channel.UNLIMITED)
    private val intents = intentChannel.receiveAsFlow()

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                when (intent) {
                    is ForecastIntent.LoadSevenDaysForecast -> getDailyForecast(intent.cityId)
                }
            }
        }
    }

    private fun getDailyForecast(cityId: String) {
        viewModelScope.launch(Dispatchers.IO){
            getDailyWeatherUseCase(cityId).collect{ resource ->
                withContext(Dispatchers.Main){
                    when(resource){
                        is Resource.Loading -> _state.value = WeatherForecastViewState.Loading
                        is Resource.Success -> _state.value =
                            WeatherForecastViewState.Success(resource.data)
                        is Resource.Failure -> _state.value = WeatherForecastViewState.Error(
                            resource.exception.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }

    fun sendIntent(intent: ForecastIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

}