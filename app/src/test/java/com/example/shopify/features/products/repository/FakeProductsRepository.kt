package com.example.shopify.features.products.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.features.products.model.fakeOptions
import com.example.shopify.features.products.model.fakeProducts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProductsRepository : IProductsRepository {
    override suspend fun getProductsByCollection(collectionId: Long): Flow<List<Product>> {
        return flow { emit(fakeProducts) }
    }

    override suspend fun getFilterOptions(collectionId: Long): List<String?> {
        return fakeOptions
    }
}