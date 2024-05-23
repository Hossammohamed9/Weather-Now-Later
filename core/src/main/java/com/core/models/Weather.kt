package com.core.models

data class Weather(
    val cityId: String,
    val lat: Double,
    val lng: Double,
    val timezone: String,
    val current: Current,
    val nextSevenDays: List<Daily>
)

data class Current(
    val time: Long,
    val temp: Double,
    val description: String,
    val icon: String
)

data class Daily(
    val time: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val icon: String
)
