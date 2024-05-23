package com.features.seven_days_forecast.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.features.seven_days_forecast.presentation.mvi.ForecastIntent
import com.features.seven_days_forecast.presentation.mvi.WeatherForecastViewModel
import com.features.seven_days_forecast.presentation.mvi.WeatherForecastViewState

@Composable
fun WeatherForecast(viewModel: WeatherForecastViewModel, cityId: String){
    val viewState = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.sendIntent(ForecastIntent.LoadSevenDaysForecast(cityId))
    }

    when(viewState){
        is WeatherForecastViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is WeatherForecastViewState.Success -> {
            Surface(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewState.dailyWeatherList.drop(1)) {
                        Text(
                            text = it.maxTemp.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
        is WeatherForecastViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = viewState.errorMessage)
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = cityId)
    }
}