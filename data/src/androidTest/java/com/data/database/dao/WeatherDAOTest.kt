package com.data.database.dao

import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WeatherDAOTest{

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Inject
//    @Named("db_name")
//    lateinit var database: AppDatabase
//    private lateinit var dao: WeatherDAO
//
//    @Before
//    fun setup() {
//        hiltRule.inject()
//        dao = database.weatherDao()
//    }
//
//    @Test
//    fun insertRepoDetailsAndRetrieveIt_returnSameRepoDetailsWithTheCorrectValues() = runBlocking {
//        val current = Current(1L, 9.9, "", "")
//        val dailyList = listOf(Daily(1L, 0.0, 0.0, "", ""))
//        val weatherEntity = WeatherEntity("1", 2.2, 2.2, "cairo", current, dailyList)
//        dao.insertWeatherOfLocation(weatherEntity)
//        val details = dao.getWeatherForCity("1")
//        assertEquals(details?.timezone, "cairo")
//    }
//
//
//    @After
//    fun teardown() {
//        database.close()
//    }

}