package com.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    val lat: Double,
    val lon: Double,
    val timezone: String,

    @SerializedName("timezone_offset")
    val timezoneOffset: Long,

    val current: CurrentWeather,
    val daily: List<DailyWeather>
)

data class CurrentWeather(
    val dt: Long,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double ?= null,

    val pressure: Long?= null,
    val humidity: Long?= null,

    @SerializedName("dew_point")
    val dewPoint: Double?= null,

    val uvi: Double?= null,
    val clouds: Long?= null,
    val visibility: Long?= null,

    @SerializedName("wind_speed")
    val windSpeed: Double?= null,

    @SerializedName("wind_deg")
    val windDeg: Long?= null,

    val weather: List<WeatherInfo>,

    @SerializedName("wind_gust")
    val windGust: Double? = null,

    val pop: Double? = null
)

data class DailyWeather(
    val dt: Long,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: Temp,

    val pressure: Long,
    val humidity: Long,

    @SerializedName("dew_point")
    val dewPoint: Double,

    val uvi: Double,
    val clouds: Long,
    val visibility: Long,

    @SerializedName("wind_speed")
    val windSpeed: Double,

    @SerializedName("wind_deg")
    val windDeg: Long,

    val weather: List<WeatherInfo>,

    @SerializedName("wind_gust")
    val windGust: Double? = null,

    val pop: Double? = null
)

data class WeatherInfo(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)