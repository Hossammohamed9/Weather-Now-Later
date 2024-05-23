package com.core.navigation

enum class NestedNavigationRoute(val route: String) {
    CITY_INPUT("city"),
    CURRENT_WEATHER("current"),
    FORECAST("forecast")
}

enum class CityInputRoute(val route: String) {
    CITY_INPUT("city_input")
}

enum class CurrentWeatherRoute(val route: String) {
    CURRENT_WEATHER("current_weather")
}

enum class ForecastRoute(val route: String) {
    FORECAST("forecast_weather")
}