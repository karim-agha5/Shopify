package com.example.shopify.features.checkout.data.remote

import com.example.shopify.core.util.Constants
import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckoutRemoteService {

    @POST("orders.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}",
        "${Constants.CONTENT_TYPE_HEADER_KEY}: ${Constants.CONTENT_TYPE}"
    )
    suspend fun createOrder(@Body body: CheckoutOrderRequest) : CheckoutOrderResponse
}