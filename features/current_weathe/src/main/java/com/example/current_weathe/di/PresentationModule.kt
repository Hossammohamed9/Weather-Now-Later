package com.example.current_weathe.di

import com.data.repository.current_weather.CurrentWeatherRepository
import com.example.current_weathe.navigation.CurrentWeatherFeatureApi
import com.example.current_weathe.navigation.CurrentWeatherFeatureApiImpl
import com.example.current_weathe.use_cases.FetchCityDetailsUseCase
import com.example.current_weathe.use_cases.FetchCurrentWeatherUseCase
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