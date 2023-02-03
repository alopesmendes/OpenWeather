package fr.open.weather.domain.use_case

import fr.open.weather.common.Resource
import fr.open.weather.domain.model.BaseState
import fr.open.weather.domain.model.WeatherData
import fr.open.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetWeatherOfCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(
        progressBar: Int,
        onStateChange: (BaseState<WeatherData>) -> Unit,
    ) {
        val cityName = when(progressBar) {
            0 -> "Rennes"
            10 -> "Paris"
            20 -> "Nantes"
            30 -> "Bordeaux"
            40 -> "Lyon"
            50 -> "Marseille"
            else -> ""
        }
        weatherRepository
            .getWeather(cityName)
            .collectLatest { resource ->
                if (resource !is Resource.Loading) {
                    onStateChange(
                        BaseState(data = resource.data, message = resource.message)
                    )
                }
            }
    }
}