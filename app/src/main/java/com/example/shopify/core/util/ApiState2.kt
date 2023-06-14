package com.example.shopify.core.util

sealed class ApiState2<T> {
    data class Success<T>(val data: T) : ApiState2<T>()
    data class Failure(val msg: Throwable) : ApiState2<Nothing>()
    object Loading : ApiState2<Nothing>()
}

