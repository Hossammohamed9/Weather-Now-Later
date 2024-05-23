package com.features.seven_days_forecast.presentation.mvi

sealed class ForecastIntent {
    data class LoadSevenDaysForecast(val cityId: String) : ForecastIntent()
}