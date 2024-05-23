package com.features.current_weather.domain.di

import com.data.repository.current_weather.CurrentWeatherRepository
import com.features.current_weather.domain.use_cases.FetchCityDetailsUseCase
import com.features.current_weather.domain.use_cases.FetchCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {


    @Provides
    fun provideFetchCityDetailsUseCase(currentWeatherRepository: CurrentWeatherRepository): FetchCityDetailsUseCase {
        return FetchCityDetailsUseCase(currentWeatherRepository)
    }

    @Provides
    fun provideFetchCurrentWeatherUseCase(currentWeatherRepository: CurrentWeatherRepository): FetchCurrentWeatherUseCase {
        return FetchCurrentWeatherUseCase(currentWeatherRepository)

    }
}