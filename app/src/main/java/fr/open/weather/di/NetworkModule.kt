package fr.open.weather.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.open.weather.data.remote.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create()
    }

    private val TAG = NetworkModule::class.simpleName

    private fun errorInterceptor(): Interceptor? {
        return try {
            Interceptor { chain ->
                val response = chain.proceed(chain.request())
                when (response.code) {
                    401, 403 -> {
                        Log.e(TAG, "errorInterceptor: ${response.code}")
                    }
                }
                response
            }
        } catch (e: Exception) {
            null
        }
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return try {
            var httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            val errorInterceptor = errorInterceptor()
            if (errorInterceptor != null)
                httpClient = httpClient.addInterceptor(errorInterceptor)

            httpClient
                .build()
        } catch (e: Exception) {
            val httpClient = OkHttpClient.Builder()
            httpClient.build()
        }

    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(
        retrofit: Retrofit.Builder
    ): WeatherApi = retrofit
        .build()
        .create(WeatherApi::class.java)
}