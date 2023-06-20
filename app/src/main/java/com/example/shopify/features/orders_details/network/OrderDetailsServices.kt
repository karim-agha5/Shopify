package com.example.shopify.features.orders_details.network

import com.example.shopify.core.common.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OrderDetailsServices {

    @GET("products.json")
    suspend fun getProductsByIds(@Header("X-Shopify-Access-Token") AccessToken : String, @Query("ids") ids: String): ProductsResponse

}