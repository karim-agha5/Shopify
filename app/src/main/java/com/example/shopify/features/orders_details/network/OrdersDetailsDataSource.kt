package com.example.shopify.features.orders_details.network

import com.example.shopify.core.common.data.model.ProductsResponse

interface OrdersDetailsDataSource {

    suspend fun getProductsByIds(ids: String) : ProductsResponse

}