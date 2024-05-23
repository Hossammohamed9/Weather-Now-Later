package com.features.city_input.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.FeatureApi

interface CityInputFeatureApi : FeatureApi

class CityInputFeatureApiImpl : CityInputFeatureApi{
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalCityInputFeatureApi.registerGraph(navHostController, navGraphBuilder)
    }

}