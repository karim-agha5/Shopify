package com.example.shopify.features.orders.repository

import com.example.shopify.core.common.data.model.CustomerId
import com.example.shopify.features.orders.model.model_response.OrderResponseData
import kotlinx.coroutines.flow.Flow

interface IOrdersRepository {
    suspend fun getOrdersByCustomerId(customerId: CustomerId) : Flow<List<OrderResponseData>>
}