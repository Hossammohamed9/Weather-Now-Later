package com.features.city_input.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.utils.common.navigation.FeatureApi

interface CityInputApi : FeatureApi

class CityInputApiImpl : CityInputApi{
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        TODO("Not yet implemented")
    }

}