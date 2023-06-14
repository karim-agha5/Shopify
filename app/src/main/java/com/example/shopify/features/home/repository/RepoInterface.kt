package com.example.shopify.features.home.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    suspend fun getBrands() : Flow<List<SmartCollection>>
    suspend fun getLimitedProducts(limit: Int) : Flow<List<Product>>
    suspend fun getProductsByCollection(collectionId: Long) : Flow<List<Product>>
}