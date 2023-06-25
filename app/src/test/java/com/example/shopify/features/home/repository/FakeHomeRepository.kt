package com.example.shopify.features.home.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.features.home.model.fakeSmartCollectionList
import com.example.shopify.features.products.model.fakeProducts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeRepository : IHomeRepository {
    override suspend fun getBrands(): Flow<List<SmartCollection>> {
        return flow { emit(fakeSmartCollectionList) }
    }

    override suspend fun getLimitedProducts(limit: Int): Flow<List<Product>> {
        return flow { emit(fakeProducts.take(limit)) }
    }

    override suspend fun getAllProducts(): Flow<List<Product>> {
        return flow { emit(fakeProducts) }
    }
}