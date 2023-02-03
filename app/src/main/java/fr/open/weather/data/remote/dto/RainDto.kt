package fr.open.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RainDto(
    @SerializedName("1h")
    val value: Double
)