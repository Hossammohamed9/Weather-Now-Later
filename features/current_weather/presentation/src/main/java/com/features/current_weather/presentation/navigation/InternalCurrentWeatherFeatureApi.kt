package com.features.current_weather.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.core.navigation.CurrentWeatherRoute
import com.core.navigation.FeatureApi
import com.core.navigation.NestedNavigationRoute
import com.features.current_weather.presentation.screens.CurrentWeather
import com.features.current_weather.presentation.screens.CurrentWeatherViewModel

object InternalCurrentWeatherFeatureApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(startDestination = CurrentWeatherRoute.CURRENT_WEATHER.route, route = NestedNavigationRoute.CURRENT_WEATHER.route){
            composable(route = CurrentWeatherRoute.CURRENT_WEATHER.route + "/{cityId}", arguments = listOf(navArgument(name = "cityId") {type = NavType.StringType})){ backStackEntry ->
                val viewModel = hiltViewModel<CurrentWeatherViewModel>()
                CurrentWeather(viewModel, navHostController, backStackEntry.arguments?.getString("cityId")!!)
            }
        }
    }
}