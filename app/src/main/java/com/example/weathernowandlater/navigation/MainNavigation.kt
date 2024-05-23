package com.example.weathernowandlater.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.navigation.NestedNavigationRoute

@Composable
fun MainNavigation(navHostController: NavHostController, navigationProvider: NavigationProvider){
    NavHost(navController = navHostController, startDestination = NestedNavigationRoute.CITY_INPUT.route ){
        navigationProvider.cityInputApi.registerGraph(navHostController, this)
        navigationProvider.currentWeatherApi.registerGraph(navHostController, this)
        navigationProvider.weatherForecastApi.registerGraph(navHostController, this)
    }
}