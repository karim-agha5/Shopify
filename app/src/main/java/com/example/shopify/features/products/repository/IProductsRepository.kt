package com.example.shopify.features.products.repository

import com.example.shopify.core.common.data.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductsRepository {
    suspend fun getProductsByCollection(collectionId: Long) : Flow<List<Product>>
    suspend fun getFilterOptions(collectionId: Long): List<String?>
}