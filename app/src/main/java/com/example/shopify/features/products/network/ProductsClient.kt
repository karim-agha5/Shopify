package com.example.shopify.features.products.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.remote.retrofit.RetrofitHelper
import com.example.shopify.core.util.Constants

class ProductsClient private constructor() : ProductsDataSource{

    private val productsServices: ProductsServices by lazy {
        RetrofitHelper.getInstance().create(ProductsServices::class.java)
    }

    override suspend fun downloadProductsByCollection(collectionId: Long): ProductsResponse {
        return productsServices.getProductsByCollectionId(Constants.password,collectionId)
    }

    companion object {
        private var instance: ProductsClient? = null
        fun getInstance(): ProductsClient {
            return instance ?: synchronized(this) {
                val temp = ProductsClient()
                instance = temp
                temp
            }
        }
    }
}