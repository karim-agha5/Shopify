package com.example.shopify.features.orders.repository

import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import com.example.shopify.features.orders.network.OrdersDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrdersRepository private constructor(private val remoteSource: OrdersDataSource) :
    IOrdersRepository {

    companion object {
        private var instance: OrdersRepository? = null
        fun getInstance(remoteSource: OrdersDataSource): OrdersRepository {
            return instance ?: synchronized(this) {
                val temp = OrdersRepository(remoteSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getOrdersByCustomerId(customerId: CustomerId): Flow<List<OrderResponseData>> {
        return flow { emit(remoteSource.getOrdersById(customerId.id).orders) }
    }
}