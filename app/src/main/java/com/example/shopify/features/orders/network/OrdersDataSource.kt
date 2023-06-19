package com.example.shopify.features.orders.network


import com.example.shopify.features.orders.model.model_response.OrderResponse

interface OrdersDataSource {

    suspend fun getOrdersById(customerId : Long) : OrderResponse
    //suspend fun makeOrderWithCustomerId(orderRequest: OrderRequest): Flow<OrderResponse>
}