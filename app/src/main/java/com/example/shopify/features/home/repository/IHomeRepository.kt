package com.example.shopify.features.home.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    suspend fun getBrands() : Flow<List<SmartCollection>>
    suspend fun getLimitedProducts(limit: Int) : Flow<List<Product>>

}