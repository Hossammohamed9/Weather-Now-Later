package com.features.city_input.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.navigation.CityInputRoute
import com.core.navigation.FeatureApi
import com.core.navigation.NestedNavigationRoute
import com.features.city_input.presentation.screens.CityInputScreen
import com.features.city_input.presentation.screens.CityInputViewModel

object InternalCityInputFeatureApi : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(startDestination = CityInputRoute.CITY_INPUT.route, route = NestedNavigationRoute.CITY_INPUT.route){
            composable(route = CityInputRoute.CITY_INPUT.route){
                val viewModel = hiltViewModel<CityInputViewModel>()
                CityInputScreen(navHostController, viewModel)
            }
        }
    }
}