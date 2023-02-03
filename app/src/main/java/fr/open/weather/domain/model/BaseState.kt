package fr.open.weather.domain.model

data class BaseState<T>(
    val data: T? = null,
    val message: String? = null,
)
