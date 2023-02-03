package fr.open.weather.data.repository

import android.content.Context
import android.util.Log
import fr.open.weather.BuildConfig
import fr.open.weather.R
import fr.open.weather.common.Resource
import fr.open.weather.data.remote.WeatherApi
import fr.open.weather.domain.model.WeatherData
import fr.open.weather.domain.repository.WeatherRepository
import fr.open.weather.mapper.OpenWeatherDtoToWeatherData.mapTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val context: Context,
) : WeatherRepository {
    companion object {
        private val TAG = WeatherRepositoryImpl::class.simpleName
    }

    override fun getWeather(
        cityName: String,
    ): Flow<Resource<WeatherData>> = flow {
        emit(Resource.Loading())
        val response = weatherApi.getWeather(
            cityCords = cityName,
            apiKey = BuildConfig.API_KEY,
            lang = Locale.getDefault().language
        )

        val resource = if (response.isSuccessful && response.body() != null) {
            Log.d(TAG, "getWeather: ${response.body()}")
            Resource.Success(
                data = response.body()!!.mapTo()
            )
        } else {
            Resource.Error(
                message = context.getString(
                    R.string.error_not_download_resource,
                    response.message()
                )
            )
        }
        emit(resource)
    }
}