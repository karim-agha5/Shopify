package com.example.shopify.core.util

import com.example.shopify.core.common.data.model.SmartCollection


sealed class ApiState {
    class Success(val smartCollection: List<SmartCollection>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading : ApiState()
}