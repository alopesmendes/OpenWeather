package fr.open.weather.domain.repository

import fr.open.weather.common.Resource
import fr.open.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(
        cityName: String,
    ): Flow<Resource<WeatherData>>
}