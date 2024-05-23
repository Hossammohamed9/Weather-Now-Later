package com.data.datasource.current_weather.local

import com.core.models.Current
import com.data.database.dao.WeatherDAO
import com.data.database.models.WeatherEntity
import com.data.mappers.toDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherLocalDSImplTest {

    @Mock
    private lateinit var weatherDAO: WeatherDAO

    private lateinit var currentWeatherLocalDSImpl: CurrentWeatherLocalDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        currentWeatherLocalDSImpl = CurrentWeatherLocalDSImpl(weatherDAO)
    }


    @Test
    fun `inserting weather entity and retrieve it, return same weather entity with correct values`() = runTest {
        // arrange
        val current = Current(1L, 9.9, "", "")
        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, emptyList())
        Mockito.`when`(weatherDAO.getWeatherForCity("1")).thenReturn(weatherEntity)
        // act
        val result = currentWeatherLocalDSImpl.getCurrentWeather("1")
        // assert
        assert(result != null)
        assert(result == weatherEntity.toDomain())
    }

}