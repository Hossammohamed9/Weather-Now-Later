package com.features.seven_days_forecast.presentation.di

import com.features.seven_days_forecast.presentation.navigation.WeatherForecastFeatureApi
import com.features.seven_days_forecast.presentation.navigation.WeatherForecastFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideWeatherForecastFeatureApi(): WeatherForecastFeatureApi{
        return WeatherForecastFeatureApiImpl()
    }
}