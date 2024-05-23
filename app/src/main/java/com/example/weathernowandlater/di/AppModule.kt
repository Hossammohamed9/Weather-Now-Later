package com.example.weathernowandlater.di

import com.example.weathernowandlater.navigation.NavigationProvider
import com.features.current_weather.navigation.CurrentWeatherFeatureApi
import com.features.input_city.navigation.CityInputFeatureApi
import com.features.seven_days_forecast.navigation.WeatherForecastFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationProvider(cityInputFeatureApi: CityInputFeatureApi, currentWeatherFeatureApi: CurrentWeatherFeatureApi, weatherForecastFeatureApi: WeatherForecastFeatureApi): NavigationProvider {
        return NavigationProvider(cityInputFeatureApi, currentWeatherFeatureApi, weatherForecastFeatureApi)
    }

}