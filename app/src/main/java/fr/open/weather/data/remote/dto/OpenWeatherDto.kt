package fr.open.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OpenWeatherDto(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    @SerializedName("coord")
    val cord: CordDto,
    val dt: Int,
    val id: Int,
    val main: MainDto,
    val name: String,
    val rain: RainDto,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)