package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.util.Constants
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DraftOrderRemoteService {
    @POST("draft_orders.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}",
        "${Constants.CONTENT_TYPE_HEADER_KEY}: ${Constants.CONTENT_TYPE}"
    )
    suspend fun createDraftOrder(@Body body: CreateDraftOrderRequestBody) : CreateDraftOrderResponse

    @GET("draft_orders/{draftOrderId}.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}"
    )
    suspend fun getDraftOrder(@Path("draftOrderId") draftOrderId: String) : ModifyDraftOrderResponse


    @PUT("draft_orders/{draftOrderId}.json")
    @Headers(
        "${Constants.ACCESS_TOKEN_HEADER}: ${Constants.PASSWORD}",
        "${Constants.CONTENT_TYPE_HEADER_KEY}: ${Constants.CONTENT_TYPE}"
    )
    suspend fun modifyDraftOrder(
        @Path("draftOrderId") draftOrderId: String,
        @Body body: ModifyDraftOrderRequestBody
    ) : ModifyDraftOrderResponse

}
