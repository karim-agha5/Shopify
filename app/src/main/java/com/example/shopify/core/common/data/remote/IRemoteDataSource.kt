package com.example.shopify.core.common.data.remote

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse

interface IRemoteDataSource {
    suspend fun downloadBrands () : SmartCollectionResponse
    suspend fun downloadTenProducts(limit: Int) : ProductsResponse

    suspend fun downloadProductsByCollection(collectionId: Long) : ProductsResponse
}