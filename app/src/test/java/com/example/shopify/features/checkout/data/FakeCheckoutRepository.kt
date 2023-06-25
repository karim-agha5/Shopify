package com.example.shopify.features.checkout.data

import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import com.example.shopify.features.checkout.model.CheckoutOrderResponseBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCheckoutRepository : ICheckoutRepository {
    override suspend fun createOrder(body: CheckoutOrderRequest): Flow<CheckoutOrderResponse> {
        return flow {
            emit(
                CheckoutOrderResponse(CheckoutOrderResponseBody(5,""))
            )
        }
    }
}