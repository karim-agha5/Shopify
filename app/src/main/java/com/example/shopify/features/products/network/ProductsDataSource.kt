package com.example.shopify.features.products.network

import com.example.shopify.core.common.data.model.ProductsResponse

interface ProductsDataSource {
    suspend fun downloadProductsByCollection(collectionId: Long) : ProductsResponse
}