package com.example.shopify.features.orders_details.repository

import com.example.shopify.features.orders.model.model_response.LineItem
import com.example.shopify.features.orders_details.network.OrdersDetailsDataSource


class OrdersDetailsRepository private constructor(private val remoteSource: OrdersDetailsDataSource) :
    IOrdersDetailsRepository {

    companion object {
        private var instance: OrdersDetailsRepository? = null
        fun getInstance(remoteSource: OrdersDetailsDataSource): OrdersDetailsRepository {
            return instance ?: synchronized(this) {
                val temp = OrdersDetailsRepository(remoteSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getImagesByProductsIds(variants: List<LineItem>): List<String> {
        val productIds: List<Long> = variants.map { it.product_id }
        val idsString: String = productIds.joinToString(",")
        val products = remoteSource.getProductsByIds(idsString).products
        val images = mutableListOf<String>()
        for (i in variants.indices){
            images.add(products[i].image.src)
        }
        return images
    }

}