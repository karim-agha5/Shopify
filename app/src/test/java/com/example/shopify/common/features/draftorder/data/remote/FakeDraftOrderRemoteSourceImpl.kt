package com.example.shopify.common.features.draftorder.data.remote

import com.example.shopify.common.features.draftorder.model.appliedDiscount
import com.example.shopify.common.features.draftorder.model.billingAddress
import com.example.shopify.common.features.draftorder.model.customer
import com.example.shopify.common.features.draftorder.model.draftOrder
import com.example.shopify.common.features.draftorder.model.fakeModifyDraftOrderResponse
import com.example.shopify.common.features.draftorder.model.lineItems
import com.example.shopify.common.features.draftorder.model.shippingAddress
import com.example.shopify.core.common.features.draftorder.data.remote.IDraftOrderRemoteSource
import com.example.shopify.core.common.features.draftorder.model.creation.request.CreateDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponse
import com.example.shopify.core.common.features.draftorder.model.creation.response.CreateDraftOrderResponseDraftOrder
import com.example.shopify.core.common.features.draftorder.model.modification.request.ModifyDraftOrderRequestBody
import com.example.shopify.core.common.features.draftorder.model.modification.response.ModifyDraftOrderResponse

class FakeDraftOrderRemoteSourceImpl: IDraftOrderRemoteSource {
    override suspend fun createShoppingCart(body: CreateDraftOrderRequestBody): CreateDraftOrderResponse {
        val fakeResponse = CreateDraftOrderResponse(draftOrder)
        return fakeResponse
    }

    override suspend fun getShoppingCart(draftOrderId: String): ModifyDraftOrderResponse {
        return fakeModifyDraftOrderResponse
    }

    override suspend fun modifyShoppingCart(
        draftOrderId: String,
        body: ModifyDraftOrderRequestBody
    ): ModifyDraftOrderResponse {
        return fakeModifyDraftOrderResponse
    }
}