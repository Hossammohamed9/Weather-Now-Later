package com.example.current_weathe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.FeatureApi

interface CurrentWeatherFeatureApi : FeatureApi

class CurrentWeatherFeatureApiImpl : CurrentWeatherFeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalCurrentWeatherFeatureApi.registerGraph(navHostController, navGraphBuilder)
    }

}