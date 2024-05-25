package com.features.current_weather.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.core.navigation.CityInputRoute
import com.core.navigation.ForecastRoute
import com.features.current_weather.R
import com.hossam.formatting_library.formatUnixToHours
import com.hossam.formatting_library.tempToCelsius

@Composable
fun CurrentWeather(viewModel: CurrentWeatherViewModel, navHostController: NavHostController, cityId: String) {

    Scaffold(topBar = {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(56.dp)
            ,
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.current_weather),
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }, content = {
        Surface(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            color = MaterialTheme.colorScheme.surface
        ) {
            MainContent(navHostController, viewModel, cityId)
        }
    })
}

@Composable
fun MainContent(navHostController: NavHostController, viewModel: CurrentWeatherViewModel, cityId: String){

    when (val viewState = viewModel.state.value) {
        is CurrentWeatherViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CurrentWeatherViewState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = viewState.weather.timezone, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp, top = 16.dp))
                Text(text = viewState.weather.current.time.formatUnixToHours(), fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp, top = 16.dp))
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 16.dp),
                    model = "https://openweathermap.org/img/wn/${viewState.weather.current.icon}@2x.png",
                    contentDescription = null,
                )

                Text(text = viewState.weather.current.temp.tempToCelsius(), fontSize = 32.sp, modifier = Modifier.padding(bottom = 8.dp))
                Text(text = viewState.weather.current.description, fontSize = 20.sp)

                Button(onClick = {
                    viewModel.deleteCurrentCity()
                    navHostController.navigate(CityInputRoute.CITY_INPUT.route )
                }) {
                    Text(text = "Choose another city")
                }

                Button(onClick = {
                    navHostController.navigate(ForecastRoute.FORECAST.route + "/${viewState.weather.cityId}")
                }) {
                    Text(text = "7-Day Forecast")
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