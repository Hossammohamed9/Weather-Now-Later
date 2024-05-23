package com.data.mappers

import com.core.models.Current
import com.core.models.Daily
import com.core.models.Weather
import com.data.database.models.WeatherEntity
import com.data.network.models.CurrentWeather
import com.data.network.models.DailyWeather
import com.data.network.models.Temp
import com.data.network.models.WeatherDTO
import com.data.network.models.WeatherInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MappersKtTest {

    @Test
    fun `test WeatherDTOToDomain() when mapping success, should return correct CityDetails`() = runTest {
        // arrange
        val weatherInfoList = listOf(WeatherInfo(0L, "main", "description", "icon"))

        val currentWeather = CurrentWeather(dt = 0L, temp = 0.0, weather = weatherInfoList)

        val weatherDTO = WeatherDTO(0.0, 0.0, "cairo", 0L, currentWeather, emptyList())
        val current = Current(0L, 0.0, "description", "icon")
        val weather = Weather("", 0.0, 0.0, "cairo", current, emptyList())

        // act
        val data = weatherDTO.toDomain("")

        // assert
        assertEquals(weather, data)
    }

    @Test
    fun `test DailyWeatherToDomain() when mapping success, should return correct CityDetails`() = runTest {
        // arrange
        val temp = Temp(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        val weatherInfoList = listOf(WeatherInfo(0L, "main", "description", "icon"))
        val dailyWeather = DailyWeather(dt = 0L, temp = temp, pressure = 0L, humidity= 0L, dewPoint = 0.0,
            uvi = 0.0, clouds = 0L, visibility = 0L, windSpeed = 0.0, windDeg = 0L , weather = weatherInfoList)
        val daily = Daily(0L, 0.0, 0.0, "description", "icon")

        // act
        val data = dailyWeather.toDomain()

        // assert
        assertEquals(daily, data)
    }

    @Test
    fun `test CurrentWeatherToDomain() when mapping success, should return correct Current`() = runTest {
        // arrange
        val weatherInfoList = listOf(WeatherInfo(0L, "main", "description", "icon"))
        val currentWeather = CurrentWeather(dt = 0L, temp = 0.0, weather = weatherInfoList)
        val current = Current(0L, 0.0, "description", "icon")

        val data = currentWeather.toDomain()

        // assert
        assertEquals(current, data)
    }

    @Test
    fun `test WeatherToEntity() when mapping success, should return correct WeatherEntity`() = runTest {
        // arrange
        val current = Current(0L, 0.0, "description", "icon")
        val weather = Weather("", 0.0, 0.0, "cairo", current, emptyList())
        val weatherEntity = WeatherEntity("", 0.0, 0.0, "cairo", current, emptyList())

        // act
        val data = weather.toEntity()

        // assert
        assertEquals(weatherEntity, data)
    }

    @Test
    fun `test WeatherEntityToDomain() when mapping success, should return correct Weather`() = runTest {
        // arrange
        val current = Current(0L, 0.0, "description", "icon")
        val weatherEntity = WeatherEntity("", 0.0, 0.0, "cairo", current, emptyList())
        val weather = Weather("", 0.0, 0.0, "cairo", current, emptyList())

        // act
        val data = weatherEntity.toDomain()

        // assert
        assertEquals(weather, data)
    }


}