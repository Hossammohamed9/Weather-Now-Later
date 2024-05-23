package com.seven_day_forecast.mvi

sealed class ForecastIntent {
    data class LoadSevenDaysForecast(val cityId: String) : ForecastIntent()
}