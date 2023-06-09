package com.example.shopify.core.common.data.remote

import com.example.shopify.core.common.data.model.SmartCollectionResponse

interface IRemoteDataSource {
    suspend fun downloadBrands () : SmartCollectionResponse
}