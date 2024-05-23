package com.features.input_city.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.dispatchers.DispatcherProvider
import com.core.dispatchers.TestDispatcherProvider
import com.core.exception.WeatherException
import com.core.result.Resource
import com.features.input_city.use_cases.CheckSavedCityUseCase
import com.features.input_city.use_cases.SearchCitiesUseCase
import com.google.android.libraries.places.api.model.AutocompletePrediction
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CityInputViewModelTest {

    @Mock
    private lateinit var searchCitiesUseCase: SearchCitiesUseCase
    @Mock
    private lateinit var checkSavedCityUseCase: CheckSavedCityUseCase
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var cityInputViewModel: CityInputViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcherProvider = TestDispatcherProvider()
        runTest {
            Mockito.`when`(checkSavedCityUseCase.invoke()).thenReturn(flow { emit(Resource.Failure(WeatherException.UnknownException)) })
            cityInputViewModel = CityInputViewModel(searchCitiesUseCase, checkSavedCityUseCase, dispatcherProvider)
        }
    }



    @Test
    fun `when searchCitiesUseCase returns loading, state should be loading`() = runTest {
        // arrange
        val result = flow { emit(Resource.Loading(true)) }
        Mockito.`when`(searchCitiesUseCase.invoke("aa")).thenReturn(result)
        // act
        cityInputViewModel.searchCities("aa")
        // assert
        assertEquals( cityInputViewModel.state.value, CityInputViewState.Loading)
    }

    @Test
    fun `when searchCitiesUseCase returns successful data, state should be success with data`() = runTest {
        // arrange
        val listOfCities : List<AutocompletePrediction> = mockk()
        val result = flow { emit(Resource.Success(listOfCities)) }
        Mockito.`when`(searchCitiesUseCase.invoke("aa")).thenReturn(result)
        // act
        cityInputViewModel.searchCities("aa")
        // assert
        assertEquals( cityInputViewModel.state.value, CityInputViewState.Success(listOfCities))
    }

    @Test
    fun `when searchCitiesUseCase returns failure, state should be failure with error message`() = runTest {
        // arrange
        val result = flow { emit(Resource.Failure(WeatherException.UnknownException)) }
        Mockito.`when`(searchCitiesUseCase.invoke("aa")).thenReturn(result)
        // act
        cityInputViewModel.searchCities("aa")
        // assert
        assert( cityInputViewModel.state.value is  CityInputViewState.Error)
    }


}