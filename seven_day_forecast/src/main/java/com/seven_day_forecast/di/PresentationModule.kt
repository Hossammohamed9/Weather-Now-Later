package com.seven_day_forecast.di

import com.data.repository.seven_days_forecast.WeatherForecastRepository
import com.seven_day_forecast.navigation.WeatherForecastFeatureApi
import com.seven_day_forecast.navigation.WeatherForecastFeatureApiImpl
import com.seven_day_forecast.use_cases.GetDailyWeatherUseCase
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