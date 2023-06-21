package com.example.shopify.core.common.features.draftorder.data.remote

import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse
import retrofit2.Retrofit

class DraftOrderRemoteSourceImpl(
    private val retrofit: Retrofit
) : IDraftOrderRemoteSource {

    private val draftOrderRemoteService = retrofit.create(DraftOrderRemoteService::class.java)

    override suspend fun createShoppingCart(
        body: CreateDraftOrderRequestBody
    )
    : CreateDraftOrderResponse {
        return draftOrderRemoteService.createDraftOrder(body)
    }

    override suspend fun getShoppingCart(draftOrderId: String): ModifyDraftOrderResponse {
        return draftOrderRemoteService.getDraftOrder(draftOrderId)
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse {
        return draftOrderRemoteService.modifyDraftOrder(draftOrderId,body)
    }
}