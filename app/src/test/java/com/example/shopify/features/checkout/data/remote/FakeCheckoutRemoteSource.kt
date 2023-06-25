package com.example.shopify.features.checkout.data.remote

import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import com.example.shopify.features.checkout.model.CheckoutOrderResponseBody

class FakeCheckoutRemoteSource : ICheckoutRemoteSource {
    override suspend fun createOrder(body: CheckoutOrderRequest): CheckoutOrderResponse {
        return CheckoutOrderResponse(CheckoutOrderResponseBody(5,null))
    }
}