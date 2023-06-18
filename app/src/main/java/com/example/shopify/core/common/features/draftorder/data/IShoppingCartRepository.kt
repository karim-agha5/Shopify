package com.example.shopify.core.common.features.draftorder.data

import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse

interface IShoppingCartRepository {
    suspend fun createShoppingCart(body: CreateDraftOrderRequestBody): CreateDraftOrderResponse
    suspend fun getShoppingCart(draftOrderId: String) : ModifyDraftOrderResponse
    suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse
}