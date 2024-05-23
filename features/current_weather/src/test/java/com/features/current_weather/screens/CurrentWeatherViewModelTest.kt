package com.features.current_weather.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.core.dispatchers.DispatcherProvider
import com.core.exception.WeatherException
import com.core.models.CityDetails
import com.core.models.Current
import com.core.models.Weather
import com.core.result.Resource
import com.features.current_weather.TestDispatcherProvider
import com.features.current_weather.use_cases.DeleteCurrentSavedCityUseCase
import com.features.current_weather.use_cases.FetchCityDetailsUseCase
import com.features.current_weather.use_cases.FetchCurrentWeatherUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {

    @Mock
    private lateinit var fetchCityDetailsUseCase: FetchCityDetailsUseCase
    @Mock
    private lateinit var fetchCurrentWeatherUseCase: FetchCurrentWeatherUseCase
    @Mock
    private lateinit var deleteCurrentSavedCityUseCase: DeleteCurrentSavedCityUseCase
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val savedStateHandle = SavedStateHandle(mapOf("cityId" to "123"))
        dispatcherProvider = TestDispatcherProvider()
        runTest {
            Mockito.`when`(fetchCityDetailsUseCase.invoke("123")).thenReturn(flow { emit(Resource.Loading(true))})
            currentWeatherViewModel = CurrentWeatherViewModel(fetchCityDetailsUseCase, fetchCurrentWeatherUseCase, deleteCurrentSavedCityUseCase, dispatcherProvider, savedStateHandle)
        }
    }

    @Test
    fun `when fetchCityDetailsUseCase returns loading, state should be Loading`() = runTest {
        // arrange
        val result = flow {
            emit(Resource.Loading(true))
        }
        `when`(fetchCityDetailsUseCase("123")).thenReturn(result)

        // act
        currentWeatherViewModel.fetchCityDetails("123")

        // assert
        assertEquals(CurrentWeatherViewState.Loading, currentWeatherViewModel.state.value)
    }


    @Test
    fun `when fetchCityDetailsUseCase returns failure, state should be Error`() = runTest {
        // arrange
        val result = flow {
            emit(Resource.Failure(WeatherException.UnknownException))
        }
        `when`(fetchCityDetailsUseCase(anyString())).thenReturn(result)

        // act
        currentWeatherViewModel.fetchCityDetails("123")

        // assert
        assertEquals(CurrentWeatherViewState.Error("Unknown exception"), currentWeatherViewModel.state.value)
    }


    @Test
    fun `when fetchCurrentWeatherUseCase returns success, state should be Success`() = runTest {
        // arrange
        val cityDetails = CityDetails("", "", LatLng(0.0, 0.0))
        `when`(fetchCityDetailsUseCase(anyString())).thenReturn(flow {
            emit(Resource.Success(cityDetails))
        })

        val current = Current(0L, 0.0, "", "")
        val currentWeather = Weather("", 0.0, 0.0, "", current, emptyList())
        `when`(fetchCurrentWeatherUseCase(anyString(), anyDouble(), anyDouble())).thenReturn(flow {
            emit(Resource.Success(currentWeather))
        })

        // act
        currentWeatherViewModel.fetchCityDetails("123")

        // assert
        assertEquals(CurrentWeatherViewState.Success(currentWeather), currentWeatherViewModel.state.value)
    }

    @Test
    fun `when fetchCurrentWeatherUseCase returns failure, state should be Error`() = runTest {
        val cityDetails = CityDetails("", "", LatLng(0.0, 0.0))
        val result = flow {
            emit(Resource.Success(cityDetails))
        }
        `when`(fetchCityDetailsUseCase(anyString())).thenReturn(result)

        `when`(fetchCurrentWeatherUseCase(anyString(), anyDouble(), anyDouble())).thenReturn(flow {
            emit(Resource.Failure(WeatherException.UnknownException))
        })

        currentWeatherViewModel.fetchCityDetails("123")

        assertEquals(CurrentWeatherViewState.Error("Unknown exception"), currentWeatherViewModel.state.value)
    }
}