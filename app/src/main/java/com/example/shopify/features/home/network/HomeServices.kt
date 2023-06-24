package com.example.shopify.features.home.network

import com.example.shopify.core.common.data.model.ProductsResponse
import com.example.shopify.core.common.data.model.SmartCollectionResponse
import com.example.shopify.core.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface HomeServices {
    @GET("smart_collections.json")
    suspend fun getBrands(@Header("X-Shopify-Access-Token") AccessToken : String ): SmartCollectionResponse

    @GET("products.json")
    suspend fun getTenProducts(@Header("X-Shopify-Access-Token") AccessToken : String, @Query("limit") limit:Int) : ProductsResponse

    @GET("products.json")
    @Headers("X-Shopify-Access-Token: ${Constants.PASSWORD}")
    suspend fun getAllProducts(): ProductsResponse
}