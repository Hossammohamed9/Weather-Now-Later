package com.features.city_input.presentation.di

import com.features.city_input.presentation.navigation.CityInputFeatureApi
import com.features.city_input.presentation.navigation.CityInputFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PresentationModule {

    @Provides
    fun provideCityInputFeatureApi(): CityInputFeatureApi {
        return CityInputFeatureApiImpl()
    }
}