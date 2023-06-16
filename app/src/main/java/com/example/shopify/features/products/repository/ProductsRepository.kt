package com.example.shopify.features.products.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.features.products.network.ProductsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepository private constructor(private val remoteSource: ProductsDataSource) : IProductsRepository {

    companion object {
        private var instance: ProductsRepository? = null
        fun getInstance(remoteSource: ProductsDataSource): ProductsRepository {
            return instance ?: synchronized(this) {
                val temp = ProductsRepository(remoteSource)
                instance = temp
                temp
            }
        }
    }


    override suspend fun getProductsByCollection(collectionId: Long): Flow<List<Product>> {
        return flow { emit(remoteSource.downloadProductsByCollection(collectionId).products) }
    }


    override suspend fun getFilterOptions(collectionId: Long): List<String> {
        val products = remoteSource.downloadProductsByCollection(collectionId).products
        return products.map { it.product_type }.distinct()
    }

}