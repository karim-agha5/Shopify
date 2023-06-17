package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.util.Constants
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateShoppingCartResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyShoppingCartRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.response.GetShoppingCartResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ShoppingCartRemoteService {
    @POST("draft_orders.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}",
        "${Constants.CONTENT_TYPE_HEADER_KEY}: ${Constants.CONTENT_TYPE}"
    )
    suspend fun createShoppingCart(@Body body: CreateShoppingCartRequestBody) : CreateShoppingCartResponse

    @GET("draft_orders/{draftOrderId}.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}"
    )
    suspend fun getShoppingCart(@Path("draftOrderId") draftOrderId: String) : GetShoppingCartResponse


    @PUT("draft_orders/{draftOrderId}.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}",
        "${Constants.CONTENT_TYPE_HEADER_KEY}: ${Constants.CONTENT_TYPE}"
    )
    suspend fun modifyShoppingCart(
        @Path("draftOrderId") draftOrderId: String,
        @Body body: ModifyShoppingCartRequestBody
    ) : GetShoppingCartResponse

}
