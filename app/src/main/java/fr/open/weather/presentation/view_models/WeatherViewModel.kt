package fr.open.weather.presentation.view_models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.open.weather.domain.model.BaseState
import fr.open.weather.domain.model.ProgressVisibility
import fr.open.weather.domain.model.WeatherData
import fr.open.weather.domain.use_case.GetWeatherOfCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherOfCityUseCase: GetWeatherOfCityUseCase,
) : ViewModel() {
    var progressVisibility: ProgressVisibility by mutableStateOf(ProgressVisibility.Start)
    val weatherDataBaseStates = mutableStateListOf<BaseState<WeatherData>>()

    companion object {
        private val TAG = WeatherViewModel::class.simpleName
    }

    fun launchWeatherApiCall(
        progress: Int
    ) {
        viewModelScope.launch {
            getWeatherOfCityUseCase(
                progressBar = progress,
                onStateChange = {
                    Log.d(TAG, "launchWeatherApiCall: ${it.data ?: "nullable"}")
                    weatherDataBaseStates.add(it)
                }
            )
        }
    }
}