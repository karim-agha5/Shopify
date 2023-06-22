package com.example.shopify.features.checkout.data

import com.example.shopify.features.checkout.data.remote.ICheckoutRemoteSource
import com.example.shopify.features.checkout.model.CheckoutOrderRequest
import com.example.shopify.features.checkout.model.CheckoutOrderResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckoutRepositoryImpl(
    private val checkoutRemoteSource: ICheckoutRemoteSource
) : ICheckoutRepository {
    override suspend fun createOrder(body: CheckoutOrderRequest) : Flow<CheckoutOrderResponse> {
        return flow{
            emit(checkoutRemoteSource.createOrder(body))
        }
    }

}