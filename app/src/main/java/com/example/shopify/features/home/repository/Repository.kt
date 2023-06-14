package com.example.shopify.features.home.repository

import com.example.shopify.core.common.data.model.Product
import com.example.shopify.core.common.data.model.SmartCollection
import com.example.shopify.core.common.data.remote.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository private constructor(private val remoteSource: IRemoteDataSource) : RepoInterface {

    companion object {
        private var instance: Repository? = null
        fun getInstance(remoteSource: IRemoteDataSource): Repository {
            return instance ?: synchronized(this) {
                val temp = Repository(remoteSource)
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

    override suspend fun getProductsByCollection(collectionId: Long): Flow<List<Product>> {
        return flow { emit(remoteSource.downloadProductsByCollection(collectionId).products) }
    }

}
