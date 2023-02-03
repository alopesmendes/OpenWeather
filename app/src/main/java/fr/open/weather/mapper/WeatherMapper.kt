package fr.open.weather.mapper

import fr.open.weather.data.remote.dto.OpenWeatherDto
import fr.open.weather.data.remote.dto.WeatherDto
import fr.open.weather.domain.model.Weather
import fr.open.weather.domain.model.WeatherData
import fr.open.weather.mapper.WeatherDtoToWeather.mapTo

object WeatherDtoToWeather: Mapper<WeatherDto, Weather> {
    override fun WeatherDto.mapTo(): Weather = Weather(
        description = description,
        icon = icon,
        id = id,
        main = main,
    )
}

object OpenWeatherDtoToWeatherData: Mapper<OpenWeatherDto, WeatherData> {
    override fun OpenWeatherDto.mapTo(): WeatherData = WeatherData(
        name = name,
        weathers = weather.map { it.mapTo() },
        allClouds = clouds.all,
        temp = main.temp,
        tempMax = main.tempMax,
        tempMin = main.tempMin,
    )
}