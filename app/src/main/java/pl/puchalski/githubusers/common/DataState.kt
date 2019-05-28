package pl.puchalski.githubusers.common

sealed class DataState<T> {
    class Loading<T>(val message: String? = null) : DataState<T>()
    class Loaded<T>(val data: T) : DataState<T>()
    class Error<T>(val message: String? = null) : DataState<T>()
}