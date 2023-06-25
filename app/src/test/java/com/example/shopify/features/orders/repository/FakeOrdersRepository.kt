package com.example.shopify.features.orders.repository

import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.features.orders.model.fakeOrderResponseDataList
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeOrdersRepository : IOrdersRepository {


    override suspend fun getOrdersByCustomerId(customerId: CustomerId): Flow<List<OrderResponseData>> {
        return flow {
            emit(fakeOrderResponseDataList)
        }
    }
}