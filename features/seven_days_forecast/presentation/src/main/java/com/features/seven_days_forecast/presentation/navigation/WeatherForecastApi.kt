package com.features.seven_days_forecast.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.FeatureApi

interface WeatherForecastFeatureApi : FeatureApi

class WeatherForecastFeatureApiImpl : WeatherForecastFeatureApi{
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalWeatherForecastFeatureApi.registerGraph(navHostController, navGraphBuilder)
    }

}