package com.features.current_weather.presentation.di

import com.features.current_weather.presentation.navigation.CurrentWeatherFeatureApi
import com.features.current_weather.presentation.navigation.CurrentWeatherFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PresentationModule {

    @Provides
    fun provideCurrentWeatherFeatureApi(): CurrentWeatherFeatureApi {
        return CurrentWeatherFeatureApiImpl()
    }

}