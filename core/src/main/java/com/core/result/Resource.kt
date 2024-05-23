package com.core.result

import com.core.exception.WeatherException

sealed class Resource<out Model> {
    data class Success<Model>(val data: Model) : Resource<Model>()
    data class Failure(val exception: WeatherException) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
}