package com.example.shopify.features.home.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.features.home.network.HomeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository private constructor(private val remoteSource: HomeDataSource) : IHomeRepository {

    companion object {
        private var instance: HomeRepository? = null
        fun getInstance(remoteSource: HomeDataSource): HomeRepository {
            return instance ?: synchronized(this) {
                val temp = HomeRepository(remoteSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getBrands() : Flow<List<SmartCollection>> {
        return flow { emit(remoteSource.downloadBrands().smart_collections) }
    }

    override suspend fun getLimitedProducts(limit: Int): Flow<List<Product>> {
        return flow { emit(remoteSource.downloadTenProducts(10).products) }
    }
}
