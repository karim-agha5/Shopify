package com.example.shopify.features.orders_details.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants

class OrderDetailsClient : OrdersDetailsDataSource {

    private val orderDetailsServices : OrderDetailsServices by lazy {
        RetrofitHelper.getInstance().create(OrderDetailsServices::class.java)
    }



    companion object {
        private var instance : OrderDetailsClient? = null
        fun getInstance(): OrderDetailsClient {
            return instance?: synchronized(this){
                val temp = OrderDetailsClient()
                instance = temp
                temp
            }
        }
    }

    override suspend fun getProductsByIds(ids: String): ProductsResponse {
        return orderDetailsServices.getProductsByIds(Constants.PASSWORD,ids)
    }
}