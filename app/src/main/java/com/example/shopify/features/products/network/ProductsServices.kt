package com.example.shopify.features.products.network

import com.example.shopify.core.common.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductsServices {
    @GET("products.json")
    suspend fun getProductsByCollectionId(@Header("X-Shopify-Access-Token") AccessToken : String, @Query("collection_id") collectionId:Long) : ProductsResponse
}