package com.data.di

import com.data.database.dao.WeatherDAO
import com.data.datasource.city_input.local.CityInputLocalDSImpl
import com.data.datasource.city_input.local.CityInputLocalDataSource
import com.data.datasource.city_input.remote.CityInputRemoteDSImpl
import com.data.datasource.city_input.remote.CityInputRemoteDataSource
import com.data.datasource.current_weather.local.CurrentWeatherLocalDSImpl
import com.data.datasource.current_weather.local.CurrentWeatherLocalDataSource
import com.data.datasource.current_weather.remote.CurrentWeatherRemoteDSImpl
import com.data.datasource.current_weather.remote.CurrentWeatherRemoteDatasource
import com.data.datasource.seven_days_forecast.local.WeatherForecastLocalDSImpl
import com.data.datasource.seven_days_forecast.local.WeatherForecastLocalDataSource
import com.data.network.api.CurrentWeatherApi
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideCityInputRemoteDS(placesClient: PlacesClient): CityInputRemoteDataSource {
        return CityInputRemoteDSImpl(placesClient)
    }

    @Provides
    fun provideCityInputLocalDS(weatherDAO: WeatherDAO): CityInputLocalDataSource {
        return CityInputLocalDSImpl(weatherDAO)
    }

    @Provides
    fun provideCurrentWeatherLocalDS(weatherDAO: WeatherDAO): CurrentWeatherLocalDataSource {
        return CurrentWeatherLocalDSImpl(weatherDAO)
    }

    @Provides
    fun provideCurrentWeatherRemoteDS(currentWeatherApi: CurrentWeatherApi, placesClient: PlacesClient): CurrentWeatherRemoteDatasource {
        return CurrentWeatherRemoteDSImpl(currentWeatherApi, placesClient)
    }

    @Provides
    fun provideWeatherForecastLocalDS(weatherDAO: WeatherDAO): WeatherForecastLocalDataSource {
        return WeatherForecastLocalDSImpl(weatherDAO)
    }
}