package com.core.exception

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

object ExceptionMapper {
    fun mapExceptionToWeatherException(exception: Exception): WeatherException {
        return when (exception) {
            is UnknownHostException -> WeatherException.Network.UnknownHostException
            is SocketTimeoutException -> WeatherException.Network.SocketTimeoutException
            is ConnectException -> WeatherException.Network.ConnectException
            is SSLHandshakeException -> WeatherException.Network.SSLHandshakeException
            is HttpException -> {
                if (exception.code() == 401) {
                    WeatherException.HTTP.Unauthorized(exception.code())
                } else if (exception.code() == 403) {
                    WeatherException.HTTP.AccessDenied(exception.code())
                } else if (exception.code() == 404) {
                    WeatherException.HTTP.NotFound(exception.code())
                } else if (exception.code() == 500) {
                    WeatherException.HTTP.InternalServerError(exception.code())
                } else {
                    WeatherException.HTTP.UnknownHttpError(exception.code())
                }
            }

            is IOException -> WeatherException.IOException()
            else -> WeatherException.UnknownException
        }
    }
}