package com.features.city_input.domain.di

import com.data.repository.city_input.CityInputRepository
import com.features.city_input.domain.use_cases.SearchCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideSearchCitiesUseCase(cityInputRepository: CityInputRepository): SearchCitiesUseCase {
        return SearchCitiesUseCase(cityInputRepository)
    }

}