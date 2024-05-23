package com.data.di

import com.data.datasource.city_input.local.CityInputLocalDataSource
import com.data.datasource.city_input.remote.CityInputRemoteDataSource
import com.data.datasource.current_weather.local.CurrentWeatherLocalDataSource
import com.data.datasource.current_weather.remote.CurrentWeatherRemoteDatasource
import com.data.datasource.seven_days_forecast.local.WeatherForecastLocalDataSource
import com.data.repository.city_input.CityInputRepoImpl
import com.data.repository.city_input.CityInputRepository
import com.data.repository.current_weather.CurrentWeatherRepoImpl
import com.data.repository.current_weather.CurrentWeatherRepository
import com.data.repository.seven_days_forecast.WeatherForecastRepoImpl
import com.data.repository.seven_days_forecast.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCityInputRepoImpl(cityInputRemoteDataSource: CityInputRemoteDataSource, cityInputLocalDataSource: CityInputLocalDataSource): CityInputRepository {
        return CityInputRepoImpl(cityInputRemoteDataSource, cityInputLocalDataSource)
    }

    @Provides
    fun provideCurrentWeatherRepoImpl(currentWeatherRemoteDatasource: CurrentWeatherRemoteDatasource, currentWeatherLocalDataSource: CurrentWeatherLocalDataSource): CurrentWeatherRepository {
        return CurrentWeatherRepoImpl(currentWeatherRemoteDatasource, currentWeatherLocalDataSource)
    }

    @Provides
    fun provideWeatherForecastRepoImpl(weatherForecastLocalDataSource: WeatherForecastLocalDataSource): WeatherForecastRepository {
        return WeatherForecastRepoImpl(weatherForecastLocalDataSource)
    }
}