package com.example.shopify.core.util

sealed class ApiState2<out T> {
    data class Success<out T>(val data: T) : ApiState2<T>()
    data class Failure<out T>(val exception: Throwable) : ApiState2<T>()
    object Loading : ApiState2<Nothing>()
}

