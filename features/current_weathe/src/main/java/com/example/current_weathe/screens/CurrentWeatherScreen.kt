package com.example.current_weathe.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.core.navigation.ForecastRoute

@Composable
fun CurrentWeather(viewModel: CurrentWeatherViewModel, navHostController: NavHostController, cityId: String) {
    val viewState = viewModel.state.value

    when (viewState) {
        is CurrentWeatherViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CurrentWeatherViewState.Success -> {
            Column {
                Text(text = viewState.weather.current.temp.toString())
                Button(onClick = {
                    navHostController.navigate(ForecastRoute.FORECAST.route + "/${viewState.weather.cityId}")
                }) {
                    Text(text = "7 - days forecast")
                }
            }
        }

        is CurrentWeatherViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = viewState.errorMessage)
                Button(onClick = { viewModel.fetchCityDetails(cityId) }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}