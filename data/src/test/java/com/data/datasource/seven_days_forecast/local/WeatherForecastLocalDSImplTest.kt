package com.data.datasource.seven_days_forecast.local

import com.core.models.Current
import com.core.models.Daily
import com.data.database.dao.WeatherDAO
import com.data.database.models.WeatherEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherForecastLocalDSImplTest {

    @Mock
    private lateinit var weatherDAO: WeatherDAO

    private lateinit var weatherForecastLocalDSImpl: WeatherForecastLocalDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherForecastLocalDSImpl = WeatherForecastLocalDSImpl(weatherDAO)
    }

    @Test
    fun `returning entity from database, call get, result list match list in entity is returned`() = runTest {
        // arrange
        val current = Current(1L, 9.9, "", "")
        val dailyList = listOf(Daily(1L, 9.9, 9.9, "", ""))
        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, dailyList)
        Mockito.`when`(weatherDAO.getWeatherForCity("1")).thenReturn(weatherEntity)
        // act
        val actualList = weatherForecastLocalDSImpl.getDailyWeatherForecast("1")
        // assert
        assert(!actualList.isNullOrEmpty())
        assert(actualList == weatherEntity.nextSevenDays)
    }
}