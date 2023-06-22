package com.example.shopify.features.authentication.registration.data.remote

import com.example.shopify.core.common.data.model.CustomerRegistration
import com.example.shopify.core.common.data.model.CustomerResponse
import com.example.shopify.core.common.data.model.DraftOrderRegistration
import com.example.shopify.core.common.data.model.DraftOrderResponse
import com.example.shopify.core.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface IShopifyDraftOrderApi {
    @Headers("X-Shopify-Access-Token: ${Constants.PASSWORD}", "Content-Type: application/json")
    @POST("draft_orders.json")
    suspend fun createDraftOrder(@Body draftOrder: DraftOrderRegistration): Response<DraftOrderResponse>
}