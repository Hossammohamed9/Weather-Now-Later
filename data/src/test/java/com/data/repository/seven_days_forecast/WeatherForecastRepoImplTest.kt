package com.data.repository.seven_days_forecast

import com.core.exception.WeatherException
import com.core.models.Daily
import com.core.result.Resource
import com.data.datasource.seven_days_forecast.local.WeatherForecastLocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherForecastRepoImplTest {

    @Mock
    private lateinit var weatherForecastLocalDataSource: WeatherForecastLocalDataSource

    private lateinit var weatherForecastRepoImpl: WeatherForecastRepoImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherForecastRepoImpl = WeatherForecastRepoImpl(weatherForecastLocalDataSource)
    }

    @Test
    fun `when local data source return daily list, function should return flow of resource loading then success with the list `() = runTest {
        // arrange
        val dailyList = listOf(Daily(0L, 0.0, 0.0, "", ""))
        Mockito.`when`(weatherForecastLocalDataSource.getDailyWeatherForecast("1")).thenReturn(dailyList)
        // act
        val result = weatherForecastRepoImpl.getWeatherForecast("1")
        // assert
        assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Success(dailyList))
    }

    @Test
    fun `when local data source return null, function should return flow of resource loading then failure with try again message `() = runTest {
        // arrange
        Mockito.`when`(weatherForecastLocalDataSource.getDailyWeatherForecast("1")).thenReturn(null)
        // act
        val result = weatherForecastRepoImpl.getWeatherForecast("1")
        // assert
        assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Failure(WeatherException.CustomException("Error occurred, please check your connection and try again")))
    }
}