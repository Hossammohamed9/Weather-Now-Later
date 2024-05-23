package com.data.datasource.current_weather.remote

import com.core.utils.Constants.APP_ID
import com.data.network.api.CurrentWeatherApi
import com.data.network.models.CurrentWeather
import com.data.network.models.WeatherDTO
import com.data.network.models.WeatherInfo
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherRemoteDSImplTest {

    @Mock
    private lateinit var currentWeatherApi: CurrentWeatherApi
    @Mock
    private lateinit var placesClient: PlacesClient

    private lateinit var currentWeatherRemoteDSImpl: CurrentWeatherRemoteDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        currentWeatherRemoteDSImpl = CurrentWeatherRemoteDSImpl(currentWeatherApi, placesClient)
    }

    @Test
    fun `get weather by lat lng, should return weather response successfully`() = runTest {
        // arrange
        val lat = 1.0
        val lng = 1.0
        val weatherInfoList = listOf(WeatherInfo(0L, "main", "description", "icon"))
        val currentWeather = CurrentWeather(0L, temp = 9.9, weather = weatherInfoList)
        val weatherDTO = WeatherDTO(lat, lng, "cairo/africa", 10L, currentWeather, emptyList())
        Mockito.`when`(currentWeatherApi.getCurrentWeather(lat, lng, APP_ID)).thenReturn(weatherDTO)
        // act
        val result = currentWeatherRemoteDSImpl.getWeather("1", lat, lng)
        // assert
        assert(result.timezone == "cairo/africa")
    }
}