package com.example.shopify.features.orders.network

import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants
import com.example.shopify.features.orders.model.model_response.OrderResponse

class OrderClient : OrdersDataSource {

    private val ordersServices: OrderServices by lazy {
        RetrofitHelper.getInstance().create(OrderServices::class.java)
    }

    override suspend fun getOrdersById(customerId: Long): OrderResponse {
        return ordersServices.downloadOrdersByCustomerId(Constants.PASSWORD, customerId)
    }

//    override suspend fun makeOrderWithCustomerId(orderRequest: OrderRequest): Flow<OrderResponse> {
//        return flow { emit(ordersServices.createOrderWithCustomerId(orderRequest)) }
//    }

    companion object {
        private var instance: OrderClient? = null
        fun getInstance(): OrderClient {
            return instance ?: synchronized(this) {
                val temp = OrderClient()
                instance = temp
                temp
            }
        }
    }
}