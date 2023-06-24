package com.example.shopify.features.orders.network

import com.example.shopify.features.orders.model.fakeOrderResponseDataList
import com.example.shopify.features.orders.model.model_response.OrderResponse

class FakeOrdersDataSource : OrdersDataSource {

    override suspend fun getOrdersById(customerId: Long): OrderResponse {
        return OrderResponse(fakeOrderResponseDataList)
    }
}