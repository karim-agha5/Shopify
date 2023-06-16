package com.example.shopify.features.category.network

import com.example.shopify.core.common.data.model.MainCategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryServices {

    @GET("custom_collections.json")
    suspend fun getMainCategories(@Header("X-Shopify-Access-Token") AccessToken : String ): MainCategoriesResponse
}