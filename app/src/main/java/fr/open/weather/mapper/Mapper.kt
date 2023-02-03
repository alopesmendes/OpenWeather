package fr.open.weather.mapper

interface Mapper<T, V> {
    fun T.mapTo(): V
}