package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import retrofit2.Response

interface HomeDataSource {
   suspend fun downloadBrands () : SmartCollectionResponse
   suspend fun downloadTenProducts(limit: Int): ProductsResponse
   suspend fun getAllProducts(): ProductsResponse
}