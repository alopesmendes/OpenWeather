package fr.open.weather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.open.weather.data.remote.WeatherApi
import fr.open.weather.data.repository.WeatherRepositoryImpl
import fr.open.weather.domain.repository.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        @ApplicationContext context: Context
    ): WeatherRepository = WeatherRepositoryImpl(
        weatherApi = weatherApi,
        context = context,
    )
}