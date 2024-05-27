package com.data.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.core.models.Current
import com.core.models.Daily
import com.data.database.AppDatabase
import com.data.database.models.WeatherEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherDAOTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: WeatherDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.weatherDao()
    }

    @Test
    fun insertWeatherEntity_thenRetrieveItById_shouldReturnSameWeatherEntityWithTheCorrectValues() = runBlocking {
        val current = Current(1L, 9.9, "", "")
        val dailyList = listOf(Daily(1L, 0.0, 0.0, "", ""))
        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, dailyList)
        dao.insertWeatherOfLocation(weatherEntity)
        val details = dao.getWeatherForCity("1")
        assertEquals(details?.timezone, "cairo")
    }

    @Test
    fun insertWeatherEntity_thenDeleteAllAndTryRetrieve_shouldReturnNull() = runBlocking {
        val current = Current(1L, 9.9, "", "")
        val dailyList = listOf(Daily(1L, 0.0, 0.0, "", ""))
        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, dailyList)
        dao.insertWeatherOfLocation(weatherEntity)
        dao.deleteAll()
        val details = dao.getWeatherForCity("1")
        assertEquals(details, null)
    }


    @Test
    fun insertWeatherEntity_thenGetSavedCity_shouldReturnInsertedWeatherEntity() = runBlocking {
        val current = Current(1L, 9.9, "", "")
        val dailyList = listOf(Daily(1L, 0.0, 0.0, "", ""))
        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, dailyList)
        dao.insertWeatherOfLocation(weatherEntity)
        val details = dao.getSavedCity()
        assertEquals(details?.timezone, "cairo")
    }


    @After
    fun teardown() {
        database.close()
    }

}