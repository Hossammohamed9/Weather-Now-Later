package com.core.utils.common.event_and_results

sealed class Resource<out Model> {
    data class Success<Model>(val model: Model) : Resource<Model>()
    data class Failure(val message: String) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
}