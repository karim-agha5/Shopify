package com.example.shopify.features.checkout.data.remote

import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse

interface ICheckoutRemoteSource {
    suspend fun createOrder(body: CheckoutOrderRequest) : CheckoutOrderResponse
}