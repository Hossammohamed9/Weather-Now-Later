package com.data.mappers

import com.core.models.CityDetails
import com.core.models.Current
import com.core.models.Daily
import com.core.models.Weather
import com.data.database.models.WeatherEntity
import com.data.network.models.CurrentWeather
import com.data.network.models.DailyWeather
import com.data.network.models.WeatherDTO
import com.google.android.libraries.places.api.model.Place

fun Place.toDomain(): CityDetails {
    return CityDetails(
        cityId = this.id.orEmpty(),
        name = this.name.orEmpty(),
        destination = this.latLng!!
    )
}

fun WeatherDTO.toDomain(cityId: String): Weather {
    return Weather(
        cityId = cityId,
        lat = this.lat,
        lng = this.lon,
        timezone = this.timezone,
        current = this.current.toDomain(),
        nextSevenDays = this.daily.map { it.toDomain() }
    )
}

fun DailyWeather.toDomain(): Daily {
    return Daily(
        time = this.dt,
        description = this.weather[0].description,
        icon = this.weather[0].icon,
        minTemp = this.temp.min,
        maxTemp = this.temp.max)
}

fun CurrentWeather.toDomain(): Current {
    return Current(
        time = this.dt,
        temp = this.temp,
        description = this.weather[0].description,
        icon = this.weather[0].icon
    )
}

fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        cityId = this.cityId,
        lat = this.lat,
        lng = this.lng,
        timezone = this.timezone,
        current = this.current,
        nextSevenDays = this.nextSevenDays
    )
}

fun WeatherEntity?.toDomain(): Weather?{
    return if (this == null){
        null
    }else{
        Weather(
            cityId = this.cityId,
            lat = this.lat,
            lng = this.lng,
            timezone = this.timezone,
            current = this.current,
            nextSevenDays = this.nextSevenDays
        )
    }

}