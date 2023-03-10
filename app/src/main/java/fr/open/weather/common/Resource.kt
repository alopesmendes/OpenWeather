package fr.open.weather.common

/**
 * The purpose of this class is to manage different cases
 * [Success] if the data is valid
 * [Error] if data is not valid or an error occurred
 * [Loading] if data is still loading
 */
sealed class Resource<out T>(
    open val data: T? = null,
    open val message: String? = null,
) {
    data class Success<out T>(
        @Transient override val data: T?
    ) : Resource<T>(data = data)

    data class Error<out T>(
        @Transient override val message: String,
        @Transient override val data: T? = null
    ) : Resource<T>(data = data, message = message)

    data class Loading<out T>(
        @Transient override val data: T? = null,
    ) : Resource<T>(data = data)
}
