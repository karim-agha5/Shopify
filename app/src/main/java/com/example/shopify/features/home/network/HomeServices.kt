package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface HomeServices {
    @GET("smart_collections.json")
    suspend fun getBrands(@Header("X-Shopify-Access-Token") AccessToken : String ): SmartCollectionResponse

    @GET("products.json")
    suspend fun getTenProducts(@Header("X-Shopify-Access-Token") AccessToken : String, @Query("limit") limit:Int) : ProductsResponse
}