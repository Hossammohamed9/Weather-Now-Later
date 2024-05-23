package com.features.input_city.di

import com.data.repository.city_input.CityInputRepository
import com.features.input_city.navigation.CityInputFeatureApi
import com.features.input_city.navigation.CityInputFeatureApiImpl
import com.features.input_city.use_cases.SearchCitiesUseCase
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

    @Provides
    fun provideSearchCitiesUseCase(cityInputRepository: CityInputRepository): SearchCitiesUseCase {
        return SearchCitiesUseCase(cityInputRepository)
    }
}