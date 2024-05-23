package com.data.datasource.city_input.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.libraries.places.api.net.PlacesClient
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CityInputRemoteDSImplTest {

    @Mock
    private lateinit var placesClient: PlacesClient

    private lateinit var cityInputRemoteDSImpl: CityInputRemoteDSImpl
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cityInputRemoteDSImpl = CityInputRemoteDSImpl(placesClient)
    }

//    @Test
//    fun `get auto complete places list successfully, return resource success with the list`() = runTest {
//
//        // arrange
//        val placeResponse: Task<FindAutocompletePredictionsResponse> = mockk()
//        val autoCompleteToken: AutocompleteSessionToken = mockk()
//
//        val request = FindAutocompletePredictionsRequest.builder()
//            .setSessionToken(autoCompleteToken)
//            .setQuery("cairo")
//            .setTypesFilter(listOf(PlaceTypes.CITIES))
//            .build()
//
//        Mockito.`when`(placesClient.findAutocompletePredictions(request)).thenReturn(placeResponse)
//
//
//        // act
//        val result = cityInputRemoteDSImpl.getCities("cairo")
//
//
//        // Assert
//        assert(result is Resource.Success)
//
//    }
}