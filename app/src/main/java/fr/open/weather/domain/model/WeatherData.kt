package fr.open.weather.domain.model

data class WeatherData(
    val name: String,
    val weathers: List<Weather>,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double,
    val allClouds: Int,
)
