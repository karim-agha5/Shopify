package com.example.shopify.features.checkout.data

import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import kotlinx.coroutines.flow.Flow

interface ICheckoutRepository {
    suspend fun createOrder(body: CheckoutOrderRequest) : Flow<CheckoutOrderResponse>
}