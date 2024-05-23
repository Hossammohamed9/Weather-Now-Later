package com.data.repository.city_input

import com.core.exception.WeatherException
import com.core.result.Resource
import com.data.datasource.city_input.local.CityInputLocalDataSource
import com.data.datasource.city_input.remote.CityInputRemoteDataSource
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
class CityInputRepoImplTest {

    @Mock
    private lateinit var cityInputRemoteDataSource: CityInputRemoteDataSource
    @Mock
    private lateinit var cityInputLocalDataSource: CityInputLocalDataSource

    private lateinit var cityInputRepoImpl: CityInputRepoImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cityInputRepoImpl = CityInputRepoImpl(cityInputRemoteDataSource, cityInputLocalDataSource)
    }

    @Test
    fun `when data source return resource success, then repo function return flow resource loading then success`() = runTest {
        // arrange
        Mockito.`when`(cityInputRemoteDataSource.getCities("cairo")).thenReturn(Resource.Success(listOf()))
        // act
        val result = cityInputRepoImpl.searchCities("cairo")
        // assert
        assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() is Resource.Success)
    }

    @Test
    fun `when data source return resource failure, then repo function return flow of resource loading then fail`() = runTest {
        // arrange
        Mockito.`when`(cityInputRemoteDataSource.getCities("cairo")).thenReturn(Resource.Failure(WeatherException.CustomException("error")))
        // act
        val result = cityInputRepoImpl.searchCities("cairo")
        // assert
        assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() is Resource.Failure)
        assert(result.last() == Resource.Failure(WeatherException.CustomException("error")))
    }
}