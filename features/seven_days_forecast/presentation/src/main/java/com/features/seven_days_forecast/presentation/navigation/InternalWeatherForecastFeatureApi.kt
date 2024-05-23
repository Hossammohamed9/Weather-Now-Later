package com.features.seven_days_forecast.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.core.navigation.FeatureApi
import com.core.navigation.ForecastRoute
import com.core.navigation.NestedNavigationRoute
import com.features.seven_days_forecast.presentation.mvi.WeatherForecastViewModel
import com.features.seven_days_forecast.presentation.screens.WeatherForecast

object InternalWeatherForecastFeatureApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(startDestination = ForecastRoute.FORECAST.route, route = NestedNavigationRoute.FORECAST.route){
            composable(route = ForecastRoute.FORECAST.route + "/{cityId}", arguments = listOf(navArgument(name = "cityId") {type = NavType.StringType})){
                val viewModel = hiltViewModel<WeatherForecastViewModel>()
                WeatherForecast(viewModel, it.arguments?.getString("cityId")!!)
            }
        }
    }
}