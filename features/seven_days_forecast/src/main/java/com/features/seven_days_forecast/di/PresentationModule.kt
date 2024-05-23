package com.features.seven_days_forecast.di

import com.data.repository.seven_days_forecast.WeatherForecastRepository
import com.features.seven_days_forecast.navigation.WeatherForecastFeatureApi
import com.features.seven_days_forecast.navigation.WeatherForecastFeatureApiImpl
import com.features.seven_days_forecast.use_cases.GetDailyWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideWeatherForecastFeatureApi(): WeatherForecastFeatureApi {
        return WeatherForecastFeatureApiImpl()
    }

    @Provides
    fun provideGetWeatherForecastUseCase(weatherForecastRepository: WeatherForecastRepository): GetDailyWeatherUseCase {
        return GetDailyWeatherUseCase(weatherForecastRepository)
    }
}