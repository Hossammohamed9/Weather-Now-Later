package com.features.seven_days_forecast.mvi

sealed class ForecastIntent {
    data class LoadSevenDaysForecast(val cityId: String) : ForecastIntent()
}