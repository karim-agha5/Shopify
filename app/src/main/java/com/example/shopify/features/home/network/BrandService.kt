package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.SmartCollectionResponse
import retrofit2.http.GET
import retrofit2.http.Header


interface BrandService {
    @GET("smart_collections.json")
    suspend fun getBrands(@Header("X-Shopify-Access-Token") AccessToken : String ): SmartCollectionResponse
}