package com.features.current_weather.di

import com.data.repository.current_weather.CurrentWeatherRepository
import com.features.current_weather.navigation.CurrentWeatherFeatureApi
import com.features.current_weather.navigation.CurrentWeatherFeatureApiImpl
import com.features.current_weather.use_cases.FetchCityDetailsUseCase
import com.features.current_weather.use_cases.FetchCurrentWeatherUseCase
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

    @Provides
    fun provideFetchCityDetailsUseCase(currentWeatherRepository: CurrentWeatherRepository): FetchCityDetailsUseCase {
        return FetchCityDetailsUseCase(currentWeatherRepository)
    }

    @Provides
    fun provideFetchCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): FetchCurrentWeatherUseCase {
        return FetchCurrentWeatherUseCase(currentWeatherRepository)

    }
}