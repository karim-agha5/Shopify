package com.example.shopify.core.util


sealed class ApiState {
    class Success<MyData>(val myData: MyData) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading : ApiState()
}