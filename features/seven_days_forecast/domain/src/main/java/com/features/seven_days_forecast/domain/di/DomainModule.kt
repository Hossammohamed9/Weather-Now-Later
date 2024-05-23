package com.features.seven_days_forecast.domain.di

import com.data.repository.seven_days_forecast.WeatherForecastRepository
import com.features.seven_days_forecast.domain.use_cases.GetDailyWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetWeatherForecastUseCase(weatherForecastRepository: WeatherForecastRepository): GetDailyWeatherUseCase{
        return GetDailyWeatherUseCase(weatherForecastRepository)
    }
}