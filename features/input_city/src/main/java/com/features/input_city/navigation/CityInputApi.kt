package com.features.input_city.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.FeatureApi

interface CityInputFeatureApi : FeatureApi

class CityInputFeatureApiImpl : CityInputFeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalCityInputFeatureApi.registerGraph(navHostController, navGraphBuilder)
    }

}