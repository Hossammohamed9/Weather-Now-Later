package com.data.repository.current_weather

import com.core.exception.WeatherException
import com.core.models.CityDetails
import com.core.models.Current
import com.core.models.Weather
import com.core.result.Resource
import com.data.datasource.current_weather.local.CurrentWeatherLocalDataSource
import com.data.datasource.current_weather.remote.CurrentWeatherRemoteDatasource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherRepoImplTest {

    @Mock
    private lateinit var currentWeatherRemoteDatasource: CurrentWeatherRemoteDatasource
    @Mock
    private lateinit var currentWeatherLocalDataSource: CurrentWeatherLocalDataSource

    private lateinit var currentWeatherRepoImpl: CurrentWeatherRepoImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        currentWeatherRepoImpl = CurrentWeatherRepoImpl(currentWeatherRemoteDatasource, currentWeatherLocalDataSource)
    }

    @Test
    fun `test fetchCityDetails() when data source return data successfully, should return flow of resource loading then success`() = runTest {
        // arrange
        val cityDetails = CityDetails("cairo", "hossam", LatLng(9.9, 9.9))
        Mockito.`when`(currentWeatherRemoteDatasource.fetchCityDetails("cairo")).thenReturn(Resource.Success(cityDetails))
        // act
        val result = currentWeatherRepoImpl.fetchCityDetails("cairo")
        // assert
        Assert.assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Success(cityDetails))
    }

    @Test
    fun `test fetchCityDetails() when data source fail, should return flow of resource loading then fail`() = runTest {
        // arrange
        Mockito.`when`(currentWeatherRemoteDatasource.fetchCityDetails("")).thenReturn(Resource.Failure(WeatherException.UnknownException))
        // act
        val result = currentWeatherRepoImpl.fetchCityDetails("")
        // assert
        Assert.assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Failure(WeatherException.UnknownException))
    }

    @Test
    fun `test getCurrentWeather() when data source return data successfully, should return flow of resource loading then success`() = runTest {
        // arrange
        val lat = 0.0
        val lng = 0.0
        val current = Current(1L, 9.9, "", "")
        val weather = Weather("1", 0.0, 0.0, "", current, emptyList())
        Mockito.`when`(currentWeatherLocalDataSource.getCurrentWeather("1")).thenReturn(weather)
        // act
        val result = currentWeatherRepoImpl.getCurrentWeather("1", lat, lng)
        // assert
        Assert.assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Success(weather))
    }

    @Test
    fun `test getCurrentWeather() when data source return null, should return flow of resource loading then failure`() = runTest {
        // arrange
        val lat = 0.0
        val lng = 0.0
        Mockito.`when`(currentWeatherLocalDataSource.getCurrentWeather("1")).thenReturn(null)
        // act
        val result = currentWeatherRepoImpl.getCurrentWeather("1", lat, lng)
        // assert
        Assert.assertEquals(result.first(), Resource.Loading(true))
        assert(result.last() == Resource.Failure(WeatherException.CustomException("Error occurred, please check your connection and try again")))
    }
}