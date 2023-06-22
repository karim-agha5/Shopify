package com.example.shopify.features.checkout.data.remote

import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse

class CheckoutRemoteSourceImpl(
    private val service: CheckoutRemoteService
) : ICheckoutRemoteSource {
    override suspend fun createOrder(body: CheckoutOrderRequest) : CheckoutOrderResponse {
        return service.createOrder(body)
    }
}