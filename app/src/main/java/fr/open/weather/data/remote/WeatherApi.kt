package fr.open.weather.data.remote

import fr.open.weather.data.remote.dto.OpenWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityCords: String,
        @Query("appId") apiKey: String,
        @Query("lang") lang: String? = null,
    ): Response<OpenWeatherDto>
}