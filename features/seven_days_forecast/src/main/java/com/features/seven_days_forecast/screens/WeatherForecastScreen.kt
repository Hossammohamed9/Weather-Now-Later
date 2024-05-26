package com.features.seven_days_forecast.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.core.models.Daily
import com.features.seven_days_forecast.mvi.ForecastIntent
import com.features.seven_days_forecast.mvi.WeatherForecastViewModel
import com.features.seven_days_forecast.mvi.WeatherForecastViewState
import com.hossam.formatting_library.formatUnixToDay
import com.hossam.formatting_library.tempToCelsius

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
            LazyColumn(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)) {
                items(viewState.dailyWeatherList.drop(1)) { weather ->
                    WeatherItem(weather = weather)
                }
            }
        }
        is WeatherForecastViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = viewState.errorMessage)
            }
        }
    }
}


@Composable
fun WeatherItem(weather: Daily) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = "https://openweathermap.org/img/wn/${weather.icon}@2x.png",
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = weather.time.formatUnixToDay(), fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = weather.description, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.End) {
            Text(text = "Min: ${weather.minTemp.tempToCelsius()}", color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp)
            Text(text = "Max: ${weather.maxTemp.tempToCelsius()}", color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp)
        }
    }
}