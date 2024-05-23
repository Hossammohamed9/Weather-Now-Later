package com.features.seven_days_forecast.mvi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.dispatchers.DispatcherProvider
import com.core.exception.WeatherException
import com.core.models.Daily
import com.core.result.Resource
import com.features.seven_days_forecast.TestDispatcherProvider
import com.features.seven_days_forecast.use_cases.GetDailyWeatherUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherForecastViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var getDailyWeatherUseCase: GetDailyWeatherUseCase
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var weatherForecastViewModel: WeatherForecastViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcherProvider = TestDispatcherProvider()
        weatherForecastViewModel = WeatherForecastViewModel(getDailyWeatherUseCase, dispatcherProvider)
    }



    @Test
    fun `when getDailyWeatherUseCase returns loading, state should be Loading`() = runTest {
        // Arrange
        val result = flow {
            emit(Resource.Loading(true))
        }
        `when`(getDailyWeatherUseCase(anyString())).thenReturn(result)

        // Act
        weatherForecastViewModel.sendIntent(ForecastIntent.LoadSevenDaysForecast("123"))
        this.testScheduler.advanceUntilIdle()

        // Assert
        assertEquals(WeatherForecastViewState.Loading, weatherForecastViewModel.state.value)
    }

    @Test
    fun `when getDailyWeatherUseCase returns success, state should be Success`() = runTest {
        // Arrange
        val weatherData = listOf(Daily(0L, 0.0, 0.0, "", ""))
        val result = flow {
            emit(Resource.Success(weatherData))
        }
        `when`(getDailyWeatherUseCase(anyString())).thenReturn(result)

        // Act
        weatherForecastViewModel.sendIntent(ForecastIntent.LoadSevenDaysForecast("123"))
        this.testScheduler.advanceUntilIdle()

        // Assert
        assertEquals(WeatherForecastViewState.Success(weatherData), weatherForecastViewModel.state.value)
    }

    @Test
    fun `when getDailyWeatherUseCase returns failure, state should be Error`() = runTest {
        // Arrange
        val result = flow {
            emit(Resource.Failure(WeatherException.UnknownException))
        }
        `when`(getDailyWeatherUseCase(anyString())).thenReturn(result)

        // Act
        weatherForecastViewModel.sendIntent(ForecastIntent.LoadSevenDaysForecast("123"))
        this.testScheduler.advanceUntilIdle()

        // Assert
        assertEquals(WeatherForecastViewState.Error("Unknown exception"), weatherForecastViewModel.state.value)
    }
}