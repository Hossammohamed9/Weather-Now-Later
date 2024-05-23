package com.core.models

import com.google.android.gms.maps.model.LatLng

data class CityDetails(
    val cityId: String,
    val name: String,
    val destination: LatLng
)
