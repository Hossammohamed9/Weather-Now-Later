package com.data.datasource.city_input.local

import com.core.models.Weather

interface CityInputLocalDataSource {
    suspend fun getSaveCity(): Weather?
}